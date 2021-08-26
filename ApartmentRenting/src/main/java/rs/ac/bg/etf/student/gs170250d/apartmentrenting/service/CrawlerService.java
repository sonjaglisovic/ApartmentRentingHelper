package rs.ac.bg.etf.student.gs170250d.apartmentrenting.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.crawler.FirstSiteData;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.crawler.SecondSiteData;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.crawler.WebSiteData;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.entity.Apartment;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.entity.Demand;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.repository.ApartmentRepository;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.repository.DemandRepository;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.tomtommodel.geocoding.Position;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.tomtommodel.geocoding.TomTomResponse;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.tomtommodel.routing.Route;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.tomtommodel.routing.RoutingResponse;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.tomtommodel.routing.Summary;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CrawlerService {

    public static List<Demand> processCrawling(String userId, ApartmentRepository apartmentRepository, DemandRepository demandRepository) {

        Set<String> visitedUrls = new HashSet<>();
        List<Apartment> apartmentsToAdd = new ArrayList<>();
        processing(new FirstSiteData(), apartmentRepository, visitedUrls, apartmentsToAdd);
        processing(new SecondSiteData(), apartmentRepository, visitedUrls, apartmentsToAdd);

        List<Apartment> allApartments = apartmentRepository.findAll();
        List<Long> apartmentIds = allApartments.stream().filter(apartment -> !visitedUrls.contains(apartment.getUrl())).map(Apartment::getApartmentId).collect(Collectors.toList());
        List<Demand> allDemands = demandRepository.findAll();
        allDemands.forEach(demand -> {
            demand.getApartmentList().removeIf(apartment -> apartmentIds.stream().anyMatch(apartmentId -> apartment.getApartmentId().equals(apartmentId)));
            demandRepository.save(demand);
        });
//        apartmentsToAdd = apartmentsToAdd.stream().filter(apartment -> allApartments.stream().noneMatch(existingApartment -> existingApartment.equals(apartment)))
//                .collect(Collectors.toList());
        List<Apartment> finalApartmentsToAdd = apartmentsToAdd;
        allDemands.forEach(demand -> {
            finalApartmentsToAdd.forEach(apartment -> {
                if(checkIfSuitable(demand, apartment)) {
                    apartment.getDemandList().add(demand);
                    demand.getApartmentList().add(apartment);
                }
            });
            demandRepository.save(demand);
        });
        for (Apartment apartment : apartmentsToAdd) {
            apartmentRepository.save(apartment);
        }
        apartmentRepository.deleteAllById(apartmentIds);
        return allDemands.stream().filter(demand -> demand.getUser().getEmail().equals(userId)).collect(Collectors.toList());
    }

    private static void processing(WebSiteData webSiteData, ApartmentRepository apartmentRepository, Set<String> visitedUrls,
                                   List<Apartment> apartmentsToAdd) {
        try {
            Document document = Jsoup.connect(webSiteData.getUrl()).get();

            Elements elements = document.select(webSiteData.selectString());
            for(Element element : elements) {
                String url = element.select("a").attr("href");
                visitedUrls.add(webSiteData.urlPrefix() + url);
                Optional<Apartment> apartment = apartmentRepository.findApartmentByUrl(webSiteData.urlPrefix() + url);
                if(apartment.isEmpty()) {
                    webSiteData.buildApartmentToAddObject(webSiteData.urlPrefix() + url, apartmentsToAdd);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Boolean checkIfSuitable(Demand demand, Apartment apartment) {

        boolean parkingRequired = demand.getParkingPlaceRequired() != null ? demand.getParkingPlaceRequired() : false;
        Integer priceMin = demand.getPriceMin() != null ? demand.getPriceMin() : 0;
        Integer priceMax = demand.getPriceMax() != null ? demand.getPriceMax() : Integer.MAX_VALUE;
        Double numberOfRoomsMin = demand.getNumberOfRoomsMin() != null ? demand.getNumberOfRoomsMin() : 0;
        Double numberOfRoomsMax = demand.getNumberOfRoomsMax() != null ? demand.getNumberOfRoomsMax() : Integer.MAX_VALUE;
        String heatType = demand.getHeatType() != null ? demand.getHeatType() : "-";
        Integer floorMin = demand.getFloorMin() != null ? demand.getFloorMin() : 0;
        Integer floorMax = demand.getFloorMax() != null ? demand.getFloorMax() : Integer.MAX_VALUE;
        Integer areaMin = demand.getMinArea() != null ? demand.getMinArea() : 0;
        Integer areaMax = demand.getMaxArea() != null ? demand.getMaxArea() : Integer.MAX_VALUE;

        //calculate distance between apartment and lat and lng and compare with diameter
        TomTomApiService tomTomApiService = new TomTomApiService();
        String[] apartmentLocation = apartment.getLocation().split(",");
        Position position = null;
        if(apartmentLocation.length == 2) {
            TomTomResponse tomTomResponse = tomTomApiService.getLocation("SRB", apartmentLocation[0], apartmentLocation[1]);
            if(!CollectionUtils.isEmpty(tomTomResponse.getResults())) {
                position = tomTomResponse.getResults().get(0).getPosition();
            }
        }

        Position positionTo = new Position(demand.getLat(), demand.getLng());

        return(!parkingRequired || apartment.getParking()) && apartment.getPrice() >= priceMin && apartment.getPrice() <= priceMax
            && apartment.getArea() >= areaMin && apartment.getArea() <= areaMax && apartment.getNumOfRooms() >= numberOfRoomsMin && apartment.getNumOfRooms() <= numberOfRoomsMax
            && apartment.getFloor() >= floorMin && apartment.getFloor() <= floorMax && (heatType.equals("-") || heatType.equals(apartment.getHeatingType())
            && (position == null || calculateDistanceInKilometers(position, positionTo) < demand.getDiameter()));

    }

    public static Double calculateDistanceInKilometers(Position positionFrom, Position positionTo) {

        TomTomApiService tomTomApiService = new TomTomApiService();
        RoutingResponse routingResponse = tomTomApiService.getDistance(positionFrom, positionTo);
        if(!CollectionUtils.isEmpty(routingResponse.getRoutes())) {
            Integer lengthInMeters = routingResponse.getRoutes().stream().map(Route::getSummary).map(Summary::getLengthInMeters).sorted().findFirst().orElse(0);
            return lengthInMeters * 1.0 / 1000;
        }
        return 0.0;
    }


}

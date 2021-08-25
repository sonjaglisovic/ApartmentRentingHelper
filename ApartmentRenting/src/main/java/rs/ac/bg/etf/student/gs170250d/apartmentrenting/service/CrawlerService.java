package rs.ac.bg.etf.student.gs170250d.apartmentrenting.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.crawler.SecondSiteData;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.crawler.WebSiteData;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.entity.Apartment;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.entity.Demand;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.repository.ApartmentRepository;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.repository.DemandRepository;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class CrawlerService {

    public static List<Demand> processCrawling(String userId, ApartmentRepository apartmentRepository, DemandRepository demandRepository) {

        Set<String> visitedUrls = new HashSet<>();
        List<Apartment> apartmentsToAdd = new ArrayList<>();
        //processing(new FirstSiteData(), apartmentRepository, visitedUrls, apartmentsToAdd);

        processing(new SecondSiteData(), apartmentRepository, visitedUrls, apartmentsToAdd);
        List<Apartment> allApartments = apartmentRepository.findAll();
        List<Long> apartmentIds = allApartments.stream().filter(apartment -> !visitedUrls.contains(apartment.getUrl())).map(Apartment::getApartmentId).collect(Collectors.toList());
        List<Demand> allDemands = demandRepository.findAll();
        allDemands.forEach(demand -> {
            demand.getApartmentList().removeIf(apartment -> apartmentIds.stream().anyMatch(apartmentId -> apartment.getApartmentId().equals(apartmentId)));
            demandRepository.save(demand);
        });
        apartmentsToAdd = apartmentsToAdd.stream().filter(apartment -> allApartments.stream().noneMatch(existingApartment -> similarityCheck(existingApartment, apartment)))
                .collect(Collectors.toList());
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
                break;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Boolean checkIfSuitable(Demand demand, Apartment apartment) {
        return false;
    }

    private static Boolean similarityCheck(Apartment existingApartment, Apartment newApartment) {
       return false;

    }


}

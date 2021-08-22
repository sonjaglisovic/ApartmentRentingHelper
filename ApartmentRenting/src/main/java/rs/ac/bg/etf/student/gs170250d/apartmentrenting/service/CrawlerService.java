package rs.ac.bg.etf.student.gs170250d.apartmentrenting.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.crawler.FirstSiteData;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.crawler.WebSiteData;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.entity.Apartment;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.entity.Demand;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.repository.ApartmentRepository;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class CrawlerService {

    public static List<Demand> processCrawling(String userId, ApartmentRepository apartmentRepository) {

        Set<String> visitedUrls = new HashSet<>();
        List<Apartment> apartmentsToAdd = new ArrayList<>();
        processing(new FirstSiteData(), userId, apartmentRepository, visitedUrls, apartmentsToAdd);

        //processing(new SecondSiteData(), userId);
        List<Apartment> allApartments = apartmentRepository.findAll();
        List<Long> apartmentIds = allApartments.stream().filter(apartment -> !visitedUrls.contains(apartment.getUrl())).map(Apartment::getApartmentId).collect(Collectors.toList());
        apartmentRepository.deleteAllById(apartmentIds);
        return new ArrayList<>();
    }

    private static void processing(WebSiteData webSiteData, String userId, ApartmentRepository apartmentRepository, Set<String> visitedUrls,
                                   List<Apartment> apartmentsToAdd) {
        try {
            Document document = Jsoup.connect(webSiteData.getUrl()).get();

            Elements elements = document.select(webSiteData.selectString());
            for(Element element : elements) {
                String url = element.select("a").attr("href");
                visitedUrls.add(url);
                Optional<Apartment> apartment = apartmentRepository.findApartmentByUrl(url);
                if(apartment.isEmpty()) {
                    webSiteData.buildApartmentToAddObject(webSiteData.urlPrefix() + url, apartmentsToAdd);
                }
                break;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void similarityCheck(Apartment apartment, ApartmentRepository apartmentRepository) {
        List<Apartment> apartmentList = apartmentRepository.findAll();

    }


}

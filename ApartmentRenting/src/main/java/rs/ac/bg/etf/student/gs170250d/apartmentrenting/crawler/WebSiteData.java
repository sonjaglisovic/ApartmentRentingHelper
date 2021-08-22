package rs.ac.bg.etf.student.gs170250d.apartmentrenting.crawler;

import rs.ac.bg.etf.student.gs170250d.apartmentrenting.entity.Apartment;

import java.util.List;

public interface WebSiteData {

    String urlPrefix();
    String getUrl();
    String selectString();
    void buildApartmentToAddObject(String url, List<Apartment> apartmentsToAdd);
}

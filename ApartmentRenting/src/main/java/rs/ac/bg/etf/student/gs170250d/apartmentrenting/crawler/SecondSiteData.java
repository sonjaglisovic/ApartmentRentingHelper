package rs.ac.bg.etf.student.gs170250d.apartmentrenting.crawler;

import rs.ac.bg.etf.student.gs170250d.apartmentrenting.entity.Apartment;

import java.util.List;

public class SecondSiteData implements WebSiteData {
    @Override
    public String urlPrefix() {
        return null;
    }

    @Override
    public String getUrl() {
       return null;
    }

    @Override
    public String selectString() {
        return "div.advert-list div.row-offer";
    }

    @Override
    public void buildApartmentToAddObject(String url, List<Apartment> apartmentsToAdd) {

    }
}

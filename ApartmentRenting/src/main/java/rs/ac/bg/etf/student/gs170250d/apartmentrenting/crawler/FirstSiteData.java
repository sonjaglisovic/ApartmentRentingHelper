package rs.ac.bg.etf.student.gs170250d.apartmentrenting.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.entity.Apartment;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class FirstSiteData implements WebSiteData {
    @Override
    public String urlPrefix() {
        return "https://www.nekretnine.rs";
    }

    @Override
    public String getUrl() {

        return "https://www.nekretnine.rs/stambeni-objekti/stanovi/izdavanje-prodaja/izdavanje/lista/po-stranici/50?gclid=CjwKCAjw9uKIBhA8EiwAYPUS3P2YTkKjJvNyjnIj1eh9vyGeIMFbyYoxDjEEZ1AB91OmaGtB5hqFHBoCov0QAvD_BwE";
    }

    @Override
    public String selectString() {
        return "div.advert-list div.offer";
    }

    public void buildApartmentToAddObject(String url, List<Apartment> apartmentsToAdd) {

        try {

            Thread.sleep(1000);
            Document document = Jsoup.connect(url).get();
            Elements elements = document.select("div.tabSectionContent ul li");
            Integer price = Integer.parseInt(document.select("h4.stickyBox__price").outerHtml().split(">")[1].split("EUR")[0]
                    .replaceAll(" ", ""));
            Integer area;
            try {
                area = Integer.parseInt(elements.get(0).select("span").outerHtml().split("<br>")[1].split("m")[0].replaceAll(" ", ""));
            }catch (NumberFormatException e) {
                area = 0;
            }
            Double numOfRooms;
            try {
                numOfRooms = Double.parseDouble(elements.get(1).select("span").outerHtml().split("<br>")[1].split("<")[0].replaceAll(" ", ""));
            }catch (NumberFormatException e) {
                numOfRooms = 0.0;
            }
            String heatType = elements.get(2).select("span").outerHtml().split("<br>")[1].split("<")[0].replaceAll(" ", "");
            Boolean parking = elements.get(3).select("span").outerHtml().split("<br>")[1].split("<")[0].replaceAll(" ", "").equals("Da");

            Integer floor;
            try {
                floor = Integer.parseInt(elements.get(4).select("span").outerHtml().split("<br>")[1].split("/")[0].replaceAll(" ", ""));
            }catch (NumberFormatException e) {
                floor = 0;
            }
            String images = document.select("picture.advert-picture img").attr("src");
            Elements locationElements = document.select("div.property__location ul li");
            String city = locationElements.get(2).outerHtml().split("<li>")[1].split("</li>")[0];
            String street = locationElements.get(4).outerHtml().split("<li>")[1].split("</li>")[0];
            String address = city + ", " + street;

            apartmentsToAdd.add(new Apartment(address, url, price, numOfRooms, floor, heatType.toLowerCase(Locale.ROOT), area, images, parking));

        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
}

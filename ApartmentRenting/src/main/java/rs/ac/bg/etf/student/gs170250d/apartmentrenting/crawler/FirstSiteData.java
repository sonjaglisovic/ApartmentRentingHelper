package rs.ac.bg.etf.student.gs170250d.apartmentrenting.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.entity.Apartment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FirstSiteData implements WebSiteData {
    @Override
    public String urlPrefix() {
        return "https://www.nekretnine.rs";
    }

    @Override
    public String getUrl(int page) {

        return "https://www.nekretnine.rs/stambeni-objekti/stanovi/izdavanje-prodaja/izdavanje/lista/po-stranici/20/" + (page == 1 ? "" : ("stranica/" + page));
    }

    private Double getNumberOfRoomsBasedOnDescription(String description) {

        if(description.contains("Jednosoban")) {
            return 1.0;
        } else if(description.contains("Dvosoban")) {
            return 2.0;
        } else if(description.contains("Trosoban")) {
            return 3.0;
        } else if(description.contains("Četvorosoban")) {
            return 4.0;
        } else if(description.contains("Petosoban")) {
            return 5.0;
        } else if(description.contains("Šestosoban")) {
            return 6.0;
        }

        return 0.0;
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
            if(numOfRooms == 0.0) {
                Elements apartmentDetails = document.select("div.property__amenities ul li");
                for(Element apartmentData: apartmentDetails) {
                    if(apartmentData.outerHtml().contains("Kategorija")) {
                        numOfRooms = getNumberOfRoomsBasedOnDescription(apartmentData.outerHtml());
                    }
                }
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
            String street = locationElements.size() > 4 ? locationElements.get(4).outerHtml().split("<li>")[1].split("</li>")[0] : "";
            String address = city + (street.equals("") ? "" : ", " + street);

            apartmentsToAdd.add(new Apartment(address, url, price, numOfRooms, floor, heatType.toLowerCase(Locale.ROOT), area, images, parking, new ArrayList<>()));

        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
}

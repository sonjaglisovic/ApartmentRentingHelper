package rs.ac.bg.etf.student.gs170250d.apartmentrenting.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.entity.Apartment;

import java.io.IOException;
import java.util.List;

public class SecondSiteData implements WebSiteData {
    @Override
    public String urlPrefix() {
        return "https://www.4zida.rs";
    }

    @Override
    public String getUrl() {
       return "https://www.4zida.rs/izdavanje-stanova/beograd";
    }

    @Override
    public String selectString() {
        return "div.preview-cards-container div.single-position";
    }

    @Override
    public void buildApartmentToAddObject(String url, List<Apartment> apartmentsToAdd) {

        try {
            Document document = Jsoup.connect(url).get();
            Elements elements = document.select("section app-info-item");
            Integer price = Integer.parseInt(document.select("div.prices strong").outerHtml().split(">")[1].split("&")[0]
                    .replaceAll(" ", "").replaceAll("\\.", ""));
            Boolean parking = false;
            Double numOfRooms = 0.0;
            Integer floor = 0;
            Integer area = 0;
            String heatingType = "";
            for(Element element : elements) {
                if(element.attr("label").equals("Garaža") || element.attr("label").equals("Parking")) {
                    parking = !parking && !element.select("div.value").outerHtml().equals("");
                } else if(element.attr("label").equals("Broj soba")) {
                    try {
                        numOfRooms = Double.parseDouble(element.select("div.value").outerHtml().split(">")[1].split("s")[0].replaceAll(" ", ""));
                    }catch (NumberFormatException e) {
                        numOfRooms = 0.0;
                    }
                } else if(element.attr("label").equals("Spratnost")) {
                    try {
                        floor = Integer.parseInt(element.select("div.value").outerHtml().split(">")[1].split("/")[0].replaceAll(" ", ""));
                    }catch (NumberFormatException e) {
                        floor = 0;
                    }
                } else if(element.attr("label").equals("Površina")) {
                    area = Integer.parseInt(element.select("div.value").outerHtml().split(">")[1].split("m")[0].replaceAll(" ", "")
                            .replaceAll("\n", ""));
                } else if(element.attr("label").equals("Grejanje")) {
                    heatingType = element.select("div.value").outerHtml().split(">")[1].split("<")[0].replaceAll("\n", "");
                }
            }
            String image = document.select("app-photo-gallery section source").attr("srcset");
            String street = document.select("div.address").outerHtml().split(">")[1].split("<")[0].replaceAll(" ", "")
                    .replaceAll("\n", "");

            apartmentsToAdd.add(new Apartment("Beograd, " + street, url, price, numOfRooms, floor, heatingType, area, image, parking));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
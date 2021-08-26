package rs.ac.bg.etf.student.gs170250d.apartmentrenting.service;

import org.junit.jupiter.api.Test;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.tomtommodel.geocoding.TomTomResponse;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.tomtommodel.routing.RoutingResponse;

public class TomTomServiceTest {

    @Test
    public void getLocationTest() {
        TomTomApiService tomTomApiService = new TomTomApiService();

        TomTomResponse tomTomResponse = tomTomApiService.getLocation("SRB", "Beograd", "Pecska");
        System.out.println(tomTomResponse.getResults().get(0).getAddress());
    }

    @Test
    public void getDistance() {

        TomTomApiService tomTomApiService = new TomTomApiService();
        TomTomResponse tomTomResponse = tomTomApiService.getLocation("SRB", "Beograd", "Pecska");
        TomTomResponse tomTomResponse1 = tomTomApiService.getLocation("SRB", "Cacak", "Radovana Jovanovica");
        RoutingResponse routingResponse = tomTomApiService.getDistance(tomTomResponse.getResults().get(0).getPosition(), tomTomResponse1.getResults().get(0).getPosition());
        System.out.println(routingResponse.getRoutes().get(0).getSummary().getLengthInMeters());
    }
}

package rs.ac.bg.etf.student.gs170250d.apartmentrenting.service;

import com.google.gson.Gson;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.tomtommodel.geocoding.Position;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.tomtommodel.geocoding.TomTomResponse;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.tomtommodel.routing.RoutingResponse;

@Service
public class TomTomApiService {

    RestTemplate restTemplate = new RestTemplate();

    public final String MY_API_KEY = "B6hkAefPnSwcihAngy9SSffKppzyW5kw";
    public final String URL_GEOCODING = "https://api.tomtom.com/search/2/structuredGeocode.json";
    public final String URL_ROUTING = "https://api.tomtom.com/routing/1/calculateRoute/";

    public TomTomResponse getLocation(String countryCode, String city, String address) {

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL_GEOCODING)
                .queryParam("countryCode", countryCode)
                .queryParam("streetName", address)
                .queryParam("municipality", city)
                .queryParam("key", MY_API_KEY);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        HttpEntity<String> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                String.class);

        Gson gson = new Gson();
        return gson.fromJson(response.getBody(), TomTomResponse.class);
    }

    public RoutingResponse getDistance(Position position1, Position position2) {

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL_ROUTING)
                .path(position1.getLat() + "," + position1.getLon() + ":" + position2.getLat() + "," + position2.getLon() + "/json")
                .queryParam("key", MY_API_KEY);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        HttpEntity<String> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                String.class);

        Gson gson = new Gson();
        return gson.fromJson(response.getBody(), RoutingResponse.class);
    }
}

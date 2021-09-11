package rs.ac.bg.etf.student.gs170250d.apartmentrenting.service;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.yaml.snakeyaml.util.UriEncoder;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.tomtommodel.geocoding.Position;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.tomtommodel.geocoding.TomTomResponse;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.tomtommodel.routing.RoutingResponse;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public class TomTomApiService {

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    RestTemplate distanceTemplate;

    public final String MY_API_KEY = "B6hkAefPnSwcihAngy9SSffKppzyW5kw";
    public final String URL_GEOCODING = "https://api.tomtom.com/search/2/structuredGeocode.json";
    public final String URL_ROUTING = "https://api.tomtom.com/routing/1/calculateRoute/";


    private String uft8Encode(String rawString) {

        byte[] bytes = rawString.getBytes(StandardCharsets.UTF_8);
        return new String(bytes, StandardCharsets.UTF_8);
    }

    public TomTomResponse getLocation(String countryCode, String city, String address) {

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

        UriComponentsBuilder builder = null;

        builder = UriComponentsBuilder.fromHttpUrl(URL_GEOCODING)
                .queryParam("countryCode", countryCode)
                .queryParam("streetName", address)
                .queryParam("municipality", city)
                .queryParam("key", MY_API_KEY)
                .encode();

        HttpEntity<?> entity = new HttpEntity<>(headers);

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        HttpEntity<String> response = restTemplate.exchange(
                builder.buildAndExpand().toUriString(),
                HttpMethod.GET,
                entity,
                String.class);

        Gson gson = new Gson();
        return gson.fromJson(response.getBody(), TomTomResponse.class);
    }

    public RoutingResponse getDistance(Position position1, Position position2) {

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        HttpEntity<String> response = null;
        try {
            response = restTemplate.exchange(
                    URL_ROUTING + URLEncoder.encode(position1.getLat() + uft8Encode( ",") + position1.getLon() + ":" + position2.getLat() + "," + position2.getLon(), "UTF-8") + "/json?key=" + MY_API_KEY +
                            "&avoid=unpavedRoads",
                    HttpMethod.GET,
                    entity,
                    String.class);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        return gson.fromJson(response.getBody(), RoutingResponse.class);
    }
}

package rs.ac.bg.etf.student.gs170250d.apartmentrenting.demand;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.entity.Apartment;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.entity.Demand;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.entity.UserEntity;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.model.DemandRequest;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.repository.ApartmentRepository;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.repository.DemandRepository;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.service.CrawlerService;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.service.TomTomApiService;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.tomtommodel.geocoding.TomTomResponse;

import java.util.ArrayList;
import java.util.stream.Stream;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class DemandApartmentTest {

    @Autowired
    ApartmentRepository apartmentRepository;
    @Autowired
    DemandRepository demandRepository;
    @Autowired
    TomTomApiService tomTomApiService;
    @Autowired
    CrawlerService crawlerService;

    private static Stream<Arguments> apartmentProvider() {
        return Stream.of(
                Arguments.of(new Apartment("Beograd, Pećska", null, 330, 2.5, 2, "klima, centralno", 65, null, true, new ArrayList<>())),
                Arguments.of(new Apartment("Beograd, Pećska", null, 500, 1.5, 2, "klima, centralno", 65, null, true, new ArrayList<>())),
                Arguments.of(new Apartment("Beograd, Pećska", null, 330, 1.5, 0, "klima, centralno", 65, null, true, new ArrayList<>())),
                Arguments.of(new Apartment("Beograd, Pećska", null, 330, 1.5, 2, "klima", 65, null, true, new ArrayList<>())),
                Arguments.of(new Apartment("Beograd, Pećska", null, 330, 1.5, 2, "klima, centralno", 65, null, false, new ArrayList<>())),
                Arguments.of(new Apartment("Beograd, Pećska", null, 330, 1.5, 2, "klima, centralno", 25, null, true, new ArrayList<>())),
                Arguments.of(new Apartment("Beograd, Apatinska", null, 330, 1.5, 2, "klima, centralno", 65, null, true, new ArrayList<>()))
        );
    }

    private static Stream<Arguments> nullParametersProvider() {

        return Stream.of(

                Arguments.of(new DemandRequest("First",null, null, 1.0,
                        null, 450, 1.5, 2.0, "centralno", false, 30, 120, 0, 10, "sonja@gmail.com"),
                        new Apartment("Beograd, Pećska", null, 330, 1.5, 2, "klima, centralno", 65, null, false, new ArrayList<>())),
                Arguments.of(new DemandRequest("First",null, null, 1.0,
                        150, null, 1.5, 2.0, "centralno", false, 30, 120, 0, 10, "sonja@gmail.com"),
                        new Apartment("Beograd, Pećska", null, 330, 1.5, 2, "klima, centralno", 65, null, false, new ArrayList<>())),
                Arguments.of(new DemandRequest("First",null, null, 1.0,
                        150, 450, null, 2.0, "centralno", false, 30, 120, 0, 10, "sonja@gmail.com"),
                        new Apartment("Beograd, Pećska", null, 330, 1.5, 2, "klima, centralno", 65, null, false, new ArrayList<>())),
                Arguments.of(new DemandRequest("First",null, null, 1.0,
                        150, 450, 1.5, null, "centralno", false, 30, 120, 0, 10, "sonja@gmail.com"),
                        new Apartment("Beograd, Pećska", null, 330, 1.5, 2, "klima, centralno", 65, null, false, new ArrayList<>())),
                Arguments.of(new DemandRequest("First",null, null, 1.0,
                        150, 450, 1.5, 2.0, null, false, 30, 120, 0, 10, "sonja@gmail.com"),
                        new Apartment("Beograd, Pećska", null, 330, 1.5, 2, "klima, centralno", 65, null, false, new ArrayList<>())),
                Arguments.of(new DemandRequest("First",null, null, 1.0,
                        150, 450, 1.5, 2.0, "centralno", null, 30, 120, 0, 10, "sonja@gmail.com"),
                        new Apartment("Beograd, Pećska", null, 330, 1.5, 2, "klima, centralno", 65, null, false, new ArrayList<>())),
                Arguments.of(new DemandRequest("First",null, null, 1.0,
                        150, 450, 1.5, 2.0, "centralno", false, null, 120, 0, 10, "sonja@gmail.com"),
                        new Apartment("Beograd, Pećska", null, 330, 1.5, 2, "klima, centralno", 65, null, false, new ArrayList<>())),
                Arguments.of(new DemandRequest("First",null, null, 1.0,
                        150, 450, 1.5, 2.0, "centralno", false, 30, null, 0,10, "sonja@gmail.com"),
                        new Apartment("Beograd, Pećska", null, 330, 1.5, 2, "klima, centralno", 65, null, false, new ArrayList<>())),
                Arguments.of(new DemandRequest("First",null, null, 1.0,
                        150, 450, 1.5, 2.0, "centralno", false, 30, 120, null, 10, "sonja@gmail.com"),
                        new Apartment("Beograd, Pećska", null, 330, 1.5, 2, "klima, centralno", 65, null, false, new ArrayList<>())),
                Arguments.of(new DemandRequest("First",null, null, 1.0,
                        150, 450, 1.5, 2.0, "centralno", false, 30, 120, 0, null, "sonja@gmail.com"),
                        new Apartment("Beograd, Pećska", null, 330, 1.5, 2, "klima, centralno", 65, null, false, new ArrayList<>())),
                Arguments.of(new DemandRequest("First",null, null, 1.0,
                        null, null, null, null, null, null, null, null, null, null, "sonja@gmail.com"),
                        new Apartment("Beograd, Pećska", null, 330, 1.5, 2, "klima, centralno", 65, null, false, new ArrayList<>()))

        );
    }

    @Test
    public void checkIfSuitableAllRequestFields1() {
        Apartment apartment = new Apartment("Beograd, Pećska", null, 330, 1.5, 2, "klima, centralno", 65, null, false, new ArrayList<>());
        TomTomResponse tomTomResponse = tomTomApiService.getLocation("RS", "Beograd", "Marka Oreškovića");
        DemandRequest demandRequest = new DemandRequest("First",tomTomResponse.getResults().get(0).getPosition().getLat(), tomTomResponse.getResults().get(0).getPosition().getLon(), 1.0,
                150, 450, 1.5, 2.0, "centralno", false, 30, 120, 0, 10, "sonja@gmail.com");
        UserEntity user = new UserEntity();
        user.setEmail("sonja@gmail.com");
        Demand demand = new Demand(demandRequest, user);
        Assertions.assertTrue(crawlerService.checkIfSuitable(demand, apartment));
    }

    @Test
    public void checkIfSuitableAllRequestFields2() {
        Apartment apartment = new Apartment("Beograd", null, 330, 1.5, 2, "-", 65, null, true, new ArrayList<>());
        TomTomResponse tomTomResponse = tomTomApiService.getLocation("RS", "Beograd", "Marka Oreškovića");
        DemandRequest demandRequest = new DemandRequest("Second", tomTomResponse.getResults().get(0).getPosition().getLat(), tomTomResponse.getResults().get(0).getPosition().getLon(), 1.0,
                150, 450, 1.5, 2.0, "centralno", false, 30, 120, 0, 10, "sonja@gmail.com");
        UserEntity user = new UserEntity();
        user.setEmail("sonja@gmail.com");
        Demand demand = new Demand(demandRequest, user);
        Assertions.assertTrue(crawlerService.checkIfSuitable(demand, apartment));
    }

    @ParameterizedTest
    @MethodSource(value = "nullParametersProvider")
    public void checkIfSuitableNullParameters(DemandRequest demandRequest, Apartment apartment) {
        TomTomResponse tomTomResponse = tomTomApiService.getLocation("RS", "Beograd", "Marka Oreškovića");
        demandRequest.setLat(tomTomResponse.getResults().get(0).getPosition().getLat());
        demandRequest.setLng(tomTomResponse.getResults().get(0).getPosition().getLon());
        UserEntity user = new UserEntity();
        user.setEmail("sonja@gmail.com");
        Demand demand = new Demand(demandRequest, user);
        Assertions.assertTrue(crawlerService.checkIfSuitable(demand, apartment));
    }

    @ParameterizedTest
    @MethodSource(value = "apartmentProvider")
    public void checkIfSuitableNegative(Apartment apartment) {
        TomTomResponse tomTomResponse = tomTomApiService.getLocation("RS", "Beograd", "Marka Oreškovića");
        DemandRequest demandRequest = new DemandRequest("Third",tomTomResponse.getResults().get(0).getPosition().getLat(), tomTomResponse.getResults().get(0).getPosition().getLon(), 1.0,
                150, 450, 1.5, 2.0, "centralno", true, 30, 120, 1, 10, "sonja@gmail.com");
        UserEntity user = new UserEntity();
        user.setEmail("sonja@gmail.com");
        Demand demand = new Demand(demandRequest, user);
        Assertions.assertFalse(crawlerService.checkIfSuitable(demand, apartment));
    }
}

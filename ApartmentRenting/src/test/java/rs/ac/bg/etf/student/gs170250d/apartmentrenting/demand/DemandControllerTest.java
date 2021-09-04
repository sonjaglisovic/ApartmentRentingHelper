package rs.ac.bg.etf.student.gs170250d.apartmentrenting.demand;

import com.google.gson.Gson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.controller.DemandsController;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.controller.UserController;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.entity.Apartment;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.entity.Demand;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.entity.UserEntity;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.model.DemandRequest;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.model.DemandResponse;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.model.UserRequest;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.repository.ApartmentRepository;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.repository.DemandRepository;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.repository.UserRepository;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.service.CrawlerService;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.service.TomTomApiService;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.tomtommodel.geocoding.TomTomResponse;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//@Transactional
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class DemandControllerTest {

    @Autowired
    UserController userController;
    @Autowired
    DemandsController demandsController;
    @Autowired
    TomTomApiService tomTomApiService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    DemandRepository demandRepository;
    @Autowired
    ApartmentRepository apartmentRepository;
    @Autowired
    CrawlerService crawlerService;

    @Test
    public void addDemand() {
        UserRequest userRequest = new UserRequest("sonja1@gmail.com", "sonja1234@");
        TomTomResponse tomTomResponse = tomTomApiService.getLocation("RS", "Beograd", "Marka Oreškovića");
       // ResponseEntity responseEntity = userController.register(userRequest);
        DemandRequest demandRequest = new DemandRequest("First",tomTomResponse.getResults().get(0).getPosition().getLat(), tomTomResponse.getResults().get(0).getPosition().getLon(), 10.0,
                150, null, 1.5, null, "centralno", false, 30, 120, 0, 10, "sonja1@gmail.com");
        ResponseEntity response = demandsController.addDemand(demandRequest);
        Gson gson = new Gson();

        List<DemandResponse> demandResponses = Arrays.stream(gson.fromJson(gson.toJson(response.getBody()), DemandResponse[].class)).collect(Collectors.toList());
        System.out.println(demandResponses.size());
    }

    @Test
    public void getApartmentsForSavedDemand() {
        UserRequest userRequest = new UserRequest("sonja1@gmail.com", "sonja1234@");
       // ResponseEntity responseEntity = userController.register(userRequest);
        TomTomResponse tomTomResponse = tomTomApiService.getLocation("RS", "Beograd", "Marka Oreškovića");
        DemandRequest demandRequest = new DemandRequest("First",tomTomResponse.getResults().get(0).getPosition().getLat(), tomTomResponse.getResults().get(0).getPosition().getLon(), 10.0,
                150, null, 1.5, null, "centralno", false, 30, 120, 0, 10, "sonja1@gmail.com");
        UserEntity user = userRepository.findById(userRequest.getEmail()).get();
        Demand demand = new Demand(demandRequest, user);
        user.getDemands().add(demand);
        demandRepository.save(demand);
        userRepository.save(user);
        List<Demand> demandList = crawlerService.processCrawling(userRequest.getEmail(), apartmentRepository, demandRepository);
        List<Apartment> apartmentList = apartmentRepository.findAll();
        demandsController.deleteDemand(demandList.get(0).getDemandId());
        System.out.println(demandList.size());
    }

    @Test
    public void removeApartmentTest() {
        TomTomResponse tomTomResponse = tomTomApiService.getLocation("RS", "Beograd", "Marka Oreškovića");
        DemandRequest demandRequest = new DemandRequest("First",tomTomResponse.getResults().get(0).getPosition().getLat(), tomTomResponse.getResults().get(0).getPosition().getLon(), 10.0,
                150, null, 1.5, null, "centralno", false, 30, 120, 0, 10, "sonja1@gmail.com");
        UserEntity user = userRepository.findById("sonja1@gmail.com").get();
        Demand demand = new Demand(demandRequest, user);
        user.getDemands().add(demand);
        demandRepository.save(demand);
        userRepository.save(user);
        Apartment apartment = new Apartment("Beograd, Pećska", "", 330, 2.5, 2, "klima, centralno", 65, null, true, new ArrayList<>());
        apartmentRepository.save(apartment);
        demand.getApartmentList().add(apartment);
        demandRepository.save(demand);
        crawlerService.processCrawling("sonja1@gmail.com", apartmentRepository, demandRepository);
        Assertions.assertTrue(apartmentRepository.findAll().stream().noneMatch(apartment1 -> apartment1.getUrl().equals(apartment.getUrl())));
    }

    @Test
    public void updateDemandTest() {

        TomTomResponse tomTomResponse = tomTomApiService.getLocation("RS", "Beograd", "Marka Oreškovića");

        DemandRequest demandRequest = new DemandRequest("First",tomTomResponse.getResults().get(0).getPosition().getLat(), tomTomResponse.getResults().get(0).getPosition().getLon(), 10.0,
                150, null, 1.0, 3.0, "centralno", false, 30, 65, 1, 3, "sonja1@gmail.com");
        demandsController.updateDemand(demandRequest, 167L);
        Optional<Demand> demand = demandRepository.findById(167L);
        System.out.println(demand.get().getApartmentList().size());
    }

}

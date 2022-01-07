package rs.ac.bg.etf.student.gs170250d.apartmentrenting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.entity.Apartment;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.entity.Demand;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.entity.UserEntity;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.exception.DemandNotFoundException;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.exception.UserNotFoundException;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.model.DemandRequest;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.repository.ApartmentRepository;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.repository.DemandRepository;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.repository.UserRepository;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.service.CrawlerService;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.service.DemandService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class DemandsController {

    @Autowired
    private DemandRepository demandRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ApartmentRepository apartmentRepository;
    @Autowired
    private CrawlerService crawlerService;
    @Autowired
    private DemandService demandService;

    @CrossOrigin
    @PostMapping(path = "/demand",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addDemand(@RequestBody DemandRequest demandRequest) {

        Optional<UserEntity> user = userRepository.findById(demandRequest.getUserEmail());
        if(user.isEmpty()) {
            return new ResponseEntity(new UserNotFoundException("User doesn't exist", HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
        }
        Demand demand = new Demand(demandRequest, user.get());
        demandService.refactorDemandRequest(demandRequest);
        List<Demand> demandList = demandRepository.findByUser(user.get());
        List<Apartment> allApartments = apartmentRepository.findPossiblySuitableApartments(demandRequest.getPriceMin(), demandRequest.getPriceMax(), demandRequest.getNumberOfRoomsMin(), demandRequest.getNumberOfRoomsMax(),
                demandRequest.getMinArea(), demandRequest.getMaxArea(), demandRequest.getFloorMin(), demandRequest.getFloorMax());
        List<Apartment> suitableForDemand = allApartments.stream().filter(apartment -> crawlerService.checkIfSuitable(demand, apartment)).collect(Collectors.toList());
        demand.setApartmentList(suitableForDemand);
        demandList.add(demand);
        demandRepository.save(demand);
        demandList.forEach(singleDemand -> singleDemand.setUser(null));
        demandList.forEach(singleDemand -> singleDemand.getApartmentList().forEach(apartment -> apartment.setDemandList(null)));
        demandList = demandService.getDemandsWithReversedApartments(demandList);
        return ResponseEntity.ok(demandList);
    }

    @CrossOrigin
    @PutMapping(path = "/demand/{demandId}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateDemand(@RequestBody DemandRequest demandRequest,
                                       @PathVariable Long demandId) {

        Optional<Demand> optionalDemand = demandRepository.findById(demandId);
        if(optionalDemand.isEmpty()) {
            return new ResponseEntity(new DemandNotFoundException("Demand not found", HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
        }
        Optional<UserEntity> updatedUser = userRepository.findById(demandRequest.getUserEmail());
        if(updatedUser.isEmpty()) {
            return new ResponseEntity(new UserNotFoundException("Specified user doesn't exist", HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
        }
        Demand demand = optionalDemand.get();
        demandService.updateDemand(demand, demandRequest);
        demandService.refactorDemandRequest(demandRequest);
        List<Apartment> allApartments = apartmentRepository.findPossiblySuitableApartments(demandRequest.getPriceMin(), demandRequest.getPriceMax(), demandRequest.getNumberOfRoomsMin(), demandRequest.getNumberOfRoomsMax(),
                demandRequest.getMinArea(), demandRequest.getMaxArea(), demandRequest.getFloorMin(), demandRequest.getFloorMax());
        List<Demand> demandList = demandRepository.findByUser(updatedUser.get());
        List<Apartment> suitableForDemand = allApartments.stream().filter(apartment -> crawlerService.checkIfSuitable(demand, apartment)).collect(Collectors.toList());
        demand.setApartmentList(suitableForDemand);
        demandList.stream().filter(singleDemand -> singleDemand.getDemandId().equals(demandId)).forEach(singleDemand -> singleDemand = demand);
        demand.setUser(updatedUser.get());
        demandRepository.save(demand);
        demandList.forEach(singleDemand -> singleDemand.setUser(null));
        demandList.forEach(singleDemand -> singleDemand.getApartmentList().forEach(apartment -> apartment.setDemandList(null)));
        demandList = demandService.getDemandsWithReversedApartments(demandList);
        return ResponseEntity.ok(demandList);
    }

    @CrossOrigin
    @DeleteMapping(path = "/demand/{demandId}")
    public ResponseEntity deleteDemand(@PathVariable("demandId") Long demandId) {

        Optional<Demand> optionalDemand = demandRepository.findById(demandId);
        if(optionalDemand.isEmpty()) {
            return new ResponseEntity(new DemandNotFoundException("Demand not found", HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
        }

        demandRepository.deleteById(demandId);
        return ResponseEntity.ok(HttpEntity.EMPTY);
    }

    @CrossOrigin
    @GetMapping(path = "/demands-by/user/{userId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getDemandsForUser(@PathVariable String userId) {

        Optional<UserEntity> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            return new ResponseEntity(new UserNotFoundException("Specified user doesn't exist", HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
        }
        List<Demand> demandList = demandRepository.findByUser(user.get());
        demandList.forEach(singleDemand -> singleDemand.setUser(null));
        demandList.forEach(singleDemand -> singleDemand.getApartmentList().forEach(apartment -> apartment.setDemandList(null)));
        demandList = demandService.getDemandsWithReversedApartments(demandList);
        return ResponseEntity.ok(demandList);
    }

}

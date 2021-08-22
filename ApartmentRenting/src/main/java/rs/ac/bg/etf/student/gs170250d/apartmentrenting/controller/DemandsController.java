package rs.ac.bg.etf.student.gs170250d.apartmentrenting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

@RestController
public class DemandsController {

    @Autowired
    private DemandRepository demandRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ApartmentRepository apartmentRepository;

    @PostMapping(path = "/demand",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addDemand(@RequestParam DemandRequest demandRequest) {

        Optional<UserEntity> user = userRepository.findById(demandRequest.getUserEmail());
        if(user.isEmpty()) {
            throw new UserNotFoundException("User doesn't exist", HttpStatus.NOT_FOUND);
        }

        Demand demand = new Demand(demandRequest, user.get());
        demandRepository.save(demand);
        List<Demand> demandList = CrawlerService.processCrawling(demand.getUser().getEmail(), apartmentRepository);
        return ResponseEntity.ok(demandList);
    }

    @PutMapping(path = "/demand/{demandId}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateDemand(@RequestParam DemandRequest demandRequest,
                                       @PathVariable Long demandId) {

        Optional<Demand> optionalDemand = demandRepository.findById(demandId);
        if(optionalDemand.isEmpty()) {
            throw new DemandNotFoundException("Demand not found", HttpStatus.NOT_FOUND);
        }
        Optional<UserEntity> updatedUser = userRepository.findById(demandRequest.getUserEmail());
        if(updatedUser.isEmpty()) {
            throw new UserNotFoundException("Specified user doesn't exist", HttpStatus.NOT_FOUND);
        }
        Demand demand = optionalDemand.get();
        DemandService.updateDemand(demand, demandRequest);
        demandRepository.save(demand);
        List<Demand> demandList = CrawlerService.processCrawling(demand.getUser().getEmail(), apartmentRepository);
        return ResponseEntity.ok(demandList);
    }

    @DeleteMapping(path = "/demand/{demandId}")
    public ResponseEntity deleteDemand(@PathVariable("demandId") Long demandId) {

        Optional<Demand> optionalDemand = demandRepository.findById(demandId);
        if(optionalDemand.isEmpty()) {
            throw new DemandNotFoundException("Demand not found", HttpStatus.NOT_FOUND);
        }
        //naci sve apartmane koji su vezani za taj demand i razvezati ih
        demandRepository.deleteById(demandId);
        return ResponseEntity.ok(HttpEntity.EMPTY);
    }

    @GetMapping(path = "/demands-by/user/{userId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getDemandsForUser(@PathVariable String userId) {

        return ResponseEntity.ok(CrawlerService.processCrawling(userId, apartmentRepository));
    }

}

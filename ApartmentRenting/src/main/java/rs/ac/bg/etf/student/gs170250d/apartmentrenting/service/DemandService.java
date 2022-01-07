package rs.ac.bg.etf.student.gs170250d.apartmentrenting.service;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.entity.Demand;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.model.DemandRequest;

import java.util.*;

@Service
public class DemandService {

    public void updateDemand(Demand demand, DemandRequest demandRequest) {

        demand.setLat(demandRequest.getLat());
        demand.setLng(demandRequest.getLng());
        demand.setDiameter(demandRequest.getDiameter());
        demand.setPriceMax(demandRequest.getPriceMax());
        demand.setPriceMin(demandRequest.getPriceMin());
        demand.setNumberOfRoomsMin(demandRequest.getNumberOfRoomsMin());
        demand.setNumberOfRoomsMax(demandRequest.getNumberOfRoomsMax());
        demand.setHeatType(demandRequest.getHeatType());
        demand.setParkingPlaceRequired(demandRequest.getParkingPlaceRequired());
        demand.setMinArea(demandRequest.getMinArea());
        demand.setMaxArea(demandRequest.getMaxArea());
        demand.setFloorMin(demandRequest.getFloorMin());
        demand.setFloorMax(demandRequest.getFloorMax());
        demand.setDemandName(demandRequest.getDemandName());
    }

    public void refactorDemandRequest(DemandRequest demandRequest) {
        demandRequest.setPriceMin(demandRequest.getPriceMin() != null ? demandRequest.getPriceMin() : 0);
        demandRequest.setPriceMax(demandRequest.getPriceMax() != null ? demandRequest.getPriceMax() : Integer.MAX_VALUE);
        demandRequest.setNumberOfRoomsMin(demandRequest.getNumberOfRoomsMin() != null ? demandRequest.getNumberOfRoomsMin() : 0);
        demandRequest.setNumberOfRoomsMax(demandRequest.getNumberOfRoomsMax() != null ? demandRequest.getNumberOfRoomsMax() : Integer.MAX_VALUE);
        demandRequest.setFloorMin(demandRequest.getFloorMin() != null ? demandRequest.getFloorMin() : 0);
        demandRequest.setFloorMax(demandRequest.getFloorMax() != null ? demandRequest.getFloorMax() : Integer.MAX_VALUE);
        demandRequest.setMinArea(demandRequest.getMinArea() != null ? demandRequest.getMinArea() : 0);
        demandRequest.setMaxArea(demandRequest.getMaxArea() != null ? demandRequest.getMaxArea() : Integer.MAX_VALUE);
    }

    public List<Demand> getDemandsWithReversedApartments(List<Demand> demandList) {
        List<Demand> reversedApartmentsList = new ArrayList<>(demandList);
        reversedApartmentsList.stream().forEach(demand -> {
          Collections.reverse(demand.getApartmentList());
        });
        return reversedApartmentsList;
    }
}

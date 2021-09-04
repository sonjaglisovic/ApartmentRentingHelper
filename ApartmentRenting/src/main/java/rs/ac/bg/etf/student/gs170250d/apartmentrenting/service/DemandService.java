package rs.ac.bg.etf.student.gs170250d.apartmentrenting.service;

import org.springframework.stereotype.Service;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.entity.Demand;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.model.DemandRequest;

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
}

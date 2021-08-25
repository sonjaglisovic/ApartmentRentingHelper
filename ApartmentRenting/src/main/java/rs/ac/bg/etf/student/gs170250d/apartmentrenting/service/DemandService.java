package rs.ac.bg.etf.student.gs170250d.apartmentrenting.service;

import rs.ac.bg.etf.student.gs170250d.apartmentrenting.entity.Apartment;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.entity.Demand;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.model.DemandRequest;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.repository.ApartmentRepository;

public class DemandService {

    public static void updateDemand(Demand demand, DemandRequest demandRequest) {

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
    }

    public static Boolean oneRelatedDemand(Long apartmentId, ApartmentRepository apartmentRepository) {

        Apartment apartment = apartmentRepository.getById(apartmentId);
        return Integer.valueOf(1).equals(apartment.getDemandList().size());
    }
}

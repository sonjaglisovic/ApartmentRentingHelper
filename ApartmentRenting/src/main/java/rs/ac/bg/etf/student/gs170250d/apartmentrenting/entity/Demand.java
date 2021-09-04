package rs.ac.bg.etf.student.gs170250d.apartmentrenting.entity;

import rs.ac.bg.etf.student.gs170250d.apartmentrenting.model.DemandRequest;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Demand {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long demandId;
    private String demandName;
    private Double lat;
    private Double lng;
    private Double diameter;
    private Integer priceMin;
    private Integer priceMax;
    private Double numberOfRoomsMin;
    private Double numberOfRoomsMax;
    private String heatType;
    private Boolean parkingPlaceRequired;
    private Integer minArea;
    private Integer maxArea;
    private Integer floorMin;
    private Integer floorMax;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userEmail")
    private UserEntity user;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "suitable",
        joinColumns = @JoinColumn(name = "demandId"),
        inverseJoinColumns = @JoinColumn(name = "apartmentId"))
    private List<Apartment> apartmentList = new ArrayList<>();

    public Demand() {}

    public Demand(DemandRequest demandRequest, UserEntity user) {

        this.diameter = demandRequest.getDiameter();
        this.lat = demandRequest.getLat();
        this.lng = demandRequest.getLng();
        this.priceMin = demandRequest.getPriceMin();
        this.priceMax = demandRequest.getPriceMax();
        this.numberOfRoomsMin = demandRequest.getNumberOfRoomsMin();
        this.numberOfRoomsMax = demandRequest.getNumberOfRoomsMax();
        this.heatType = demandRequest.getHeatType();
        this.parkingPlaceRequired = demandRequest.getParkingPlaceRequired();
        this.minArea = demandRequest.getMinArea();
        this.maxArea = demandRequest.getMaxArea();
        this.user = user;
        this.floorMin = demandRequest.getFloorMin();
        this.floorMax = demandRequest.getFloorMax();
        this.demandName = demandRequest.getDemandName();
    }

    public Long getDemandId() {
        return demandId;
    }

    public void setDemandId(Long demandId) {
        this.demandId = demandId;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getDiameter() {
        return diameter;
    }

    public void setDiameter(Double diameter) {
        this.diameter = diameter;
    }

    public Integer getPriceMin() {
        return priceMin;
    }

    public void setPriceMin(Integer priceMin) {
        this.priceMin = priceMin;
    }

    public Integer getPriceMax() {
        return priceMax;
    }

    public void setPriceMax(Integer priceMax) {
        this.priceMax = priceMax;
    }

    public Double getNumberOfRoomsMin() {
        return numberOfRoomsMin;
    }

    public void setNumberOfRoomsMin(Double numberOfRoomsMin) {
        this.numberOfRoomsMin = numberOfRoomsMin;
    }

    public Double getNumberOfRoomsMax() {
        return numberOfRoomsMax;
    }

    public void setNumberOfRoomsMax(Double numberOfRoomsMax) {
        this.numberOfRoomsMax = numberOfRoomsMax;
    }

    public String getHeatType() {
        return heatType;
    }

    public void setHeatType(String heatType) {
        this.heatType = heatType;
    }

    public Boolean getParkingPlaceRequired() {
        return parkingPlaceRequired;
    }

    public void setParkingPlaceRequired(Boolean parkingPlaceRequired) {
        this.parkingPlaceRequired = parkingPlaceRequired;
    }

    public Integer getMinArea() {
        return minArea;
    }

    public void setMinArea(Integer minArea) {
        this.minArea = minArea;
    }

    public Integer getMaxArea() {
        return maxArea;
    }

    public void setMaxArea(Integer maxArea) {
        this.maxArea = maxArea;
    }

    public List<Apartment> getApartmentList() {
        return apartmentList;
    }

    public void setApartmentList(List<Apartment> apartmentList) {
        this.apartmentList = apartmentList;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Integer getFloorMin() {
        return floorMin;
    }

    public void setFloorMin(Integer floorMin) {
        this.floorMin = floorMin;
    }

    public Integer getFloorMax() {
        return floorMax;
    }

    public void setFloorMax(Integer floorMax) {
        this.floorMax = floorMax;
    }

    public String getDemandName() {
        return demandName;
    }

    public void setDemandName(String demandName) {
        this.demandName = demandName;
    }
}

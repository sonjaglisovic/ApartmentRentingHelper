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
    private Double lat;
    private Double lng;
    private Double diameter;
    private Integer priceMin;
    private Integer priceMax;
    private Integer numberOfRoomsMin;
    private Integer numberOfRoomsMax;
    private String heatType;
    private Boolean parkingPlaceRequired;
    private Double minArea;
    private Double maxArea;
    @ManyToOne
    @JoinColumn(name = "userEmail")
    private UserEntity user;
    @ManyToMany
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

    public Integer getNumberOfRoomsMin() {
        return numberOfRoomsMin;
    }

    public void setNumberOfRoomsMin(Integer numberOfRoomsMin) {
        this.numberOfRoomsMin = numberOfRoomsMin;
    }

    public Integer getNumberOfRoomsMax() {
        return numberOfRoomsMax;
    }

    public void setNumberOfRoomsMax(Integer numberOfRoomsMax) {
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

    public Double getMinArea() {
        return minArea;
    }

    public void setMinArea(Double minArea) {
        this.minArea = minArea;
    }

    public Double getMaxArea() {
        return maxArea;
    }

    public void setMaxArea(Double maxArea) {
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
}
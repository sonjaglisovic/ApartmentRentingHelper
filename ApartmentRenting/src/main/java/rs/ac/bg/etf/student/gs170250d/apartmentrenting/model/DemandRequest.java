package rs.ac.bg.etf.student.gs170250d.apartmentrenting.model;

import java.io.Serializable;

public class DemandRequest implements Serializable {

    private Long demandId;
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
    private String userEmail;
    private String demandName;

    public DemandRequest() {}

    public DemandRequest(String demandName, Double lat, Double lng, Double diameter, Integer priceMin, Integer priceMax, Double numberOfRoomsMin, Double numberOfRoomsMax, String heatType, Boolean parkingPlaceRequired, Integer minArea, Integer maxArea, Integer floorMin, Integer floorMax, String userEmail) {
        this.lat = lat;
        this.lng = lng;
        this.diameter = diameter;
        this.priceMin = priceMin;
        this.priceMax = priceMax;
        this.numberOfRoomsMin = numberOfRoomsMin;
        this.numberOfRoomsMax = numberOfRoomsMax;
        this.heatType = heatType;
        this.parkingPlaceRequired = parkingPlaceRequired;
        this.minArea = minArea;
        this.maxArea = maxArea;
        this.floorMin = floorMin;
        this.floorMax = floorMax;
        this.userEmail = userEmail;
        this.demandName = demandName;
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

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
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

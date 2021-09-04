package rs.ac.bg.etf.student.gs170250d.apartmentrenting.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.entity.Apartment;
import rs.ac.bg.etf.student.gs170250d.apartmentrenting.entity.UserEntity;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DemandResponse implements Serializable {

    @SerializedName("demandId")
    @Expose
    private Long demandId;
    @SerializedName("demandName")
    @Expose
    private String demandName;
    @SerializedName("lat")
    @Expose
    private Double lat;
    @SerializedName("lng")
    @Expose
    private Double lng;
    @SerializedName("diameter")
    @Expose
    private Double diameter;
    @SerializedName("priceMin")
    @Expose
    private Integer priceMin;
    @SerializedName("priceMax")
    @Expose
    private Integer priceMax;
    @SerializedName("numberOfRoomsMin")
    @Expose
    private Double numberOfRoomsMin;
    @SerializedName("numberOfRoomsMax")
    @Expose
    private Double numberOfRoomsMax;
    @SerializedName("heatType")
    @Expose
    private String heatType;
    @SerializedName("parkingPlaceRequired")
    @Expose
    private Boolean parkingPlaceRequired;
    @SerializedName("minArea")
    @Expose
    private Integer minArea;
    @SerializedName("maxArea")
    @Expose
    private Integer maxArea;
    @SerializedName("floorMin")
    @Expose
    private Integer floorMin;
    @SerializedName("floorMax")
    @Expose
    private Integer floorMax;
    @SerializedName("user")
    @Expose
    private UserEntity user;
    @SerializedName("apartmentList")
    @Expose
    private List<Apartment> apartmentList = new ArrayList<>();


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

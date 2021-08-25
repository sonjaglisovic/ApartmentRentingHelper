package rs.ac.bg.etf.student.gs170250d.apartmentrenting.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Apartment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long apartmentId;
    private String location;
    private String url;
    private Integer price;
    private Double numOfRooms;
    private Integer floor;
    private String heatingType;
    private Integer area;
    private String image;
    private Boolean parking;
    @ManyToMany(mappedBy = "apartmentList")
    List<Demand> demandList = new ArrayList<>();

    public Apartment() {}

    public Apartment(String location, String url, Integer price, Double numOfRooms, Integer floor, String heatType, Integer area, String image, Boolean parking) {
        this.location = location;
        this.url = url;
        this.price = price;
        this.numOfRooms = numOfRooms;
        this.floor = floor;
        this.heatingType = heatType;
        this.area = area;
        this.image = image;
        this.parking = parking;
    }

    public Long getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(Long apartmentId) {
        this.apartmentId = apartmentId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Double getNumOfRooms() {
        return numOfRooms;
    }

    public void setNumOfRooms(Double numOfRooms) {
        this.numOfRooms = numOfRooms;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public String getHeatingType() {
        return heatingType;
    }

    public void setHeatingType(String heatingType) {
        this.heatingType = heatingType;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public List<Demand> getDemandList() {
        return demandList;
    }

    public void setDemandList(List<Demand> demandList) {
        this.demandList = demandList;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Boolean getParking() {
        return parking;
    }

    public void setParking(Boolean parking) {
        this.parking = parking;
    }
}

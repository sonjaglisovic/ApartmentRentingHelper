package rs.ac.bg.etf.student.gs170250d.apartmentrenting.model;

import java.io.Serializable;

public class Position implements Serializable {

    private Double lat;
    private Double lng;

    public Position(Double lat, Double lng) {
        this.lat = lat;
        this.lng = lng;
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
}

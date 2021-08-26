package rs.ac.bg.etf.student.gs170250d.apartmentrenting.tomtommodel.routing;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Summary implements Serializable {

    @SerializedName("lengthInMeters")
    @Expose
    private Integer lengthInMeters;
    @SerializedName("travelTimeInSeconds")
    @Expose
    private Integer travelTimeInSeconds;
    @SerializedName("trafficDelayInSeconds")
    @Expose
    private Integer trafficDelayInSeconds;
    @SerializedName("departureTime")
    @Expose
    private String departureTime;
    @SerializedName("arrivalTime")
    @Expose
    private String arrivalTime;
    private final static long serialVersionUID = 1440912374805369515L;

    public Integer getLengthInMeters() {
        return lengthInMeters;
    }

    public void setLengthInMeters(Integer lengthInMeters) {
        this.lengthInMeters = lengthInMeters;
    }

    public Integer getTravelTimeInSeconds() {
        return travelTimeInSeconds;
    }

    public void setTravelTimeInSeconds(Integer travelTimeInSeconds) {
        this.travelTimeInSeconds = travelTimeInSeconds;
    }

    public Integer getTrafficDelayInSeconds() {
        return trafficDelayInSeconds;
    }

    public void setTrafficDelayInSeconds(Integer trafficDelayInSeconds) {
        this.trafficDelayInSeconds = trafficDelayInSeconds;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

}

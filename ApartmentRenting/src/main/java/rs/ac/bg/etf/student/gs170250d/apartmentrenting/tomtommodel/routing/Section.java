package rs.ac.bg.etf.student.gs170250d.apartmentrenting.tomtommodel.routing;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Section implements Serializable {

    @SerializedName("startPointIndex")
    @Expose
    private Integer startPointIndex;
    @SerializedName("endPointIndex")
    @Expose
    private Integer endPointIndex;
    @SerializedName("sectionType")
    @Expose
    private String sectionType;
    @SerializedName("travelMode")
    @Expose
    private String travelMode;
    private final static long serialVersionUID = 7263143443696302134L;

    public Integer getStartPointIndex() {
        return startPointIndex;
    }

    public void setStartPointIndex(Integer startPointIndex) {
        this.startPointIndex = startPointIndex;
    }

    public Integer getEndPointIndex() {
        return endPointIndex;
    }

    public void setEndPointIndex(Integer endPointIndex) {
        this.endPointIndex = endPointIndex;
    }

    public String getSectionType() {
        return sectionType;
    }

    public void setSectionType(String sectionType) {
        this.sectionType = sectionType;
    }

    public String getTravelMode() {
        return travelMode;
    }

    public void setTravelMode(String travelMode) {
        this.travelMode = travelMode;
    }

}

package rs.ac.bg.etf.student.gs170250d.apartmentrenting.tomtommodel.routing;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Route implements Serializable {

    @SerializedName("summary")
    @Expose
    private Summary summary;
    @SerializedName("legs")
    @Expose
    private List<Leg> legs = null;
    @SerializedName("sections")
    @Expose
    private List<Section> sections = null;
    private final static long serialVersionUID = -6991517663884987862L;

    public Summary getSummary() {
        return summary;
    }

    public void setSummary(Summary summary) {
        this.summary = summary;
    }

    public List<Leg> getLegs() {
        return legs;
    }

    public void setLegs(List<Leg> legs) {
        this.legs = legs;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

}

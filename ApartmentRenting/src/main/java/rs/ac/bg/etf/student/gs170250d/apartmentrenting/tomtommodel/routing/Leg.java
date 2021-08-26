package rs.ac.bg.etf.student.gs170250d.apartmentrenting.tomtommodel.routing;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Leg implements Serializable {

    @SerializedName("summary")
    @Expose
    private Summary summary;
    @SerializedName("points")
    @Expose
    private List<Point> points = null;
    private final static long serialVersionUID = -313633429647703850L;

    public Summary getSummary() {
        return summary;
    }

    public void setSummary(Summary summary) {
        this.summary = summary;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

}

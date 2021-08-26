package rs.ac.bg.etf.student.gs170250d.apartmentrenting.tomtommodel.geocoding;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class TomTomResponse implements Serializable {

    @SerializedName("summary")
    @Expose
    private Summary summary;
    @SerializedName("results")
    @Expose
    private List<Result> results = null;
    private final static long serialVersionUID = 8721638686279034127L;

    public Summary getSummary() {
        return summary;
    }

    public void setSummary(Summary summary) {
        this.summary = summary;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

}
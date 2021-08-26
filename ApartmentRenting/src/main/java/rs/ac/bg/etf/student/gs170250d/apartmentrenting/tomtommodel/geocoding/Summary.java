package rs.ac.bg.etf.student.gs170250d.apartmentrenting.tomtommodel.geocoding;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Summary implements Serializable {

    @SerializedName("query")
    @Expose
    private String query;
    @SerializedName("queryType")
    @Expose
    private String queryType;
    @SerializedName("queryTime")
    @Expose
    private Integer queryTime;
    @SerializedName("numResults")
    @Expose
    private Integer numResults;
    @SerializedName("offset")
    @Expose
    private Integer offset;
    @SerializedName("totalResults")
    @Expose
    private Integer totalResults;
    @SerializedName("fuzzyLevel")
    @Expose
    private Integer fuzzyLevel;
    private final static long serialVersionUID = -6657018267645377608L;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getQueryType() {
        return queryType;
    }

    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }

    public Integer getQueryTime() {
        return queryTime;
    }

    public void setQueryTime(Integer queryTime) {
        this.queryTime = queryTime;
    }

    public Integer getNumResults() {
        return numResults;
    }

    public void setNumResults(Integer numResults) {
        this.numResults = numResults;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public Integer getFuzzyLevel() {
        return fuzzyLevel;
    }

    public void setFuzzyLevel(Integer fuzzyLevel) {
        this.fuzzyLevel = fuzzyLevel;
    }

}

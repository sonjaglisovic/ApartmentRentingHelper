package rs.ac.bg.etf.student.gs170250d.apartmentrenting.tomtommodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AddressRanges implements Serializable {

    @SerializedName("rangeLeft")
    @Expose
    private String rangeLeft;
    @SerializedName("rangeRight")
    @Expose
    private String rangeRight;
    @SerializedName("from")
    @Expose
    private From from;
    @SerializedName("to")
    @Expose
    private To to;
    private final static long serialVersionUID = -2091154339543920452L;

    public String getRangeLeft() {
        return rangeLeft;
    }

    public void setRangeLeft(String rangeLeft) {
        this.rangeLeft = rangeLeft;
    }

    public String getRangeRight() {
        return rangeRight;
    }

    public void setRangeRight(String rangeRight) {
        this.rangeRight = rangeRight;
    }

    public From getFrom() {
        return from;
    }

    public void setFrom(From from) {
        this.from = from;
    }

    public To getTo() {
        return to;
    }

    public void setTo(To to) {
        this.to = to;
    }

}

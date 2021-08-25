package rs.ac.bg.etf.student.gs170250d.apartmentrenting.tomtommodel;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Viewport implements Serializable {

    @SerializedName("topLeftPoint")
    @Expose
    private TopLeftPoint topLeftPoint;
    @SerializedName("btmRightPoint")
    @Expose
    private BtmRightPoint btmRightPoint;
    private final static long serialVersionUID = -7020755271454933367L;

    public TopLeftPoint getTopLeftPoint() {
        return topLeftPoint;
    }

    public void setTopLeftPoint(TopLeftPoint topLeftPoint) {
        this.topLeftPoint = topLeftPoint;
    }

    public BtmRightPoint getBtmRightPoint() {
        return btmRightPoint;
    }

    public void setBtmRightPoint(BtmRightPoint btmRightPoint) {
        this.btmRightPoint = btmRightPoint;
    }

}

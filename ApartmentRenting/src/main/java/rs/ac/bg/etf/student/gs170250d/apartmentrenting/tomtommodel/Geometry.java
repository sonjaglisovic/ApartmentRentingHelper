package rs.ac.bg.etf.student.gs170250d.apartmentrenting.tomtommodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Geometry implements Serializable {

    @SerializedName("id")
    @Expose
    private String id;
    private final static long serialVersionUID = 1515576607775640014L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}

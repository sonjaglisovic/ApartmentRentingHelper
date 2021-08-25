package rs.ac.bg.etf.student.gs170250d.apartmentrenting.tomtommodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class EntryPoint implements Serializable {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("position")
    @Expose
    private Position position;
    private final static long serialVersionUID = -7216819220467323472L;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

}
package rs.ac.bg.etf.student.gs170250d.apartmentrenting.tomtommodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Mapcode implements Serializable {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("fullMapcode")
    @Expose
    private String fullMapcode;
    @SerializedName("territory")
    @Expose
    private String territory;
    @SerializedName("code")
    @Expose
    private String code;
    private final static long serialVersionUID = -2497195842347878597L;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFullMapcode() {
        return fullMapcode;
    }

    public void setFullMapcode(String fullMapcode) {
        this.fullMapcode = fullMapcode;
    }

    public String getTerritory() {
        return territory;
    }

    public void setTerritory(String territory) {
        this.territory = territory;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}

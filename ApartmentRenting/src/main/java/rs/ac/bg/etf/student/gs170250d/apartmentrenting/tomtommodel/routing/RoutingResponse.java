package rs.ac.bg.etf.student.gs170250d.apartmentrenting.tomtommodel.routing;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class RoutingResponse implements Serializable {

    @SerializedName("formatVersion")
    @Expose
    private String formatVersion;
    @SerializedName("copyright")
    @Expose
    private String copyright;
    @SerializedName("privacy")
    @Expose
    private String privacy;
    @SerializedName("routes")
    @Expose
    private List<Route> routes = null;
    private final static long serialVersionUID = -62249113731355399L;

    public String getFormatVersion() {
        return formatVersion;
    }

    public void setFormatVersion(String formatVersion) {
        this.formatVersion = formatVersion;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getPrivacy() {
        return privacy;
    }

    public void setPrivacy(String privacy) {
        this.privacy = privacy;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

}

package rs.ac.bg.etf.student.gs170250d.apartmentrenting.tomtommodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class TomTomResponse implements Serializable {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("score")
    @Expose
    private Double score;
    @SerializedName("address")
    @Expose
    private Address address;
    @SerializedName("position")
    @Expose
    private Position position;
    @SerializedName("mapcodes")
    @Expose
    private List<Mapcode> mapcodes = null;
    @SerializedName("viewport")
    @Expose
    private Viewport viewport;
    @SerializedName("entryPoints")
    @Expose
    private List<EntryPoint> entryPoints = null;
    @SerializedName("addressRanges")
    @Expose
    private AddressRanges addressRanges;
    @SerializedName("dataSources")
    @Expose
    private DataSources dataSources;
    private final static long serialVersionUID = 6347770107929317394L;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public List<Mapcode> getMapcodes() {
        return mapcodes;
    }

    public void setMapcodes(List<Mapcode> mapcodes) {
        this.mapcodes = mapcodes;
    }

    public Viewport getViewport() {
        return viewport;
    }

    public void setViewport(Viewport viewport) {
        this.viewport = viewport;
    }

    public List<EntryPoint> getEntryPoints() {
        return entryPoints;
    }

    public void setEntryPoints(List<EntryPoint> entryPoints) {
        this.entryPoints = entryPoints;
    }

    public AddressRanges getAddressRanges() {
        return addressRanges;
    }

    public void setAddressRanges(AddressRanges addressRanges) {
        this.addressRanges = addressRanges;
    }

    public DataSources getDataSources() {
        return dataSources;
    }

    public void setDataSources(DataSources dataSources) {
        this.dataSources = dataSources;
    }

}
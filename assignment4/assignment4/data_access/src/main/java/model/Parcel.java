package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Parcel")
public class Parcel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "ParcelName")
    private String parcelName;

    @Column(name = "ParcelDescription")
    private String parcelDescription;

    @Column(name = "IsTracked")
    private int isTracked;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "ParcelSender", referencedColumnName = "Id")
    private User parcelSender;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "ParcelSenderCity", referencedColumnName = "Id")
    private City parcelSenderCity;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "ParcelReceiver", referencedColumnName = "Id")
    private User parcelReceiver;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "ParcelReceiverCity", referencedColumnName = "Id")
    private City parcelReceiverCity;

    @OneToMany
    @JoinColumn(name = "Route", referencedColumnName = "Id")
    private List<RouteEntry> route;

    public Parcel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getParcelName() {
        return parcelName;
    }

    public void setParcelName(String parcelName) {
        this.parcelName = parcelName;
    }

    public String getParcelDescription() {
        return parcelDescription;
    }

    public void setParcelDescription(String parcelDescription) {
        this.parcelDescription = parcelDescription;
    }

    public int getIsTracked() {
        return isTracked;
    }

    public void setIsTracked(int isTracked) {
        this.isTracked = isTracked;
    }

    public User getParcelSender() {
        return parcelSender;
    }

    public void setParcelSender(User parcelSender) {
        this.parcelSender = parcelSender;
    }

    public City getParcelSenderCity() {
        return parcelSenderCity;
    }

    public void setParcelSenderCity(City parcelSenderCity) {
        this.parcelSenderCity = parcelSenderCity;
    }

    public User getParcelReceiver() {
        return parcelReceiver;
    }

    public void setParcelReceiver(User parcelReceiver) {
        this.parcelReceiver = parcelReceiver;
    }

    public City getParcelReceiverCity() {
        return parcelReceiverCity;
    }

    public void setParcelReceiverCity(City parcelReceiverCity) {
        this.parcelReceiverCity = parcelReceiverCity;
    }

    public List<RouteEntry> getRoute() {
        if (route == null) {
            route = new ArrayList<RouteEntry>();
        }
        return this.route;
    }
}

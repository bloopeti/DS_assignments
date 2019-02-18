package dataAccess.model;

import javax.persistence.*;

@Entity
@Table(name = "flights")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "flightNr")
    private int flightNr;

    @Column(name = "airplaneType")
    private String airplaneType;

//    @Column(name = "departureCity")
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "departureCity", referencedColumnName = "id")
    private City departureCity;

    @Column(name = "departureTime")
    private String departureTime; //TODO

//    @Column(name = "arrivalCity")
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "arrivalCity", referencedColumnName = "id")
    private City arrivalCity;

    @Column(name = "arrivalTime")
    private String arrivalTime; //TODO


    public Flight() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFlightNr() {
        return flightNr;
    }

    public void setFlightNr(int flightNr) {
        this.flightNr = flightNr;
    }

    public String getAirplaneType() {
        return airplaneType;
    }

    public void setAirplaneType(String airplaneType) {
        this.airplaneType = airplaneType;
    }

    public City getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(City departureCity) {
        this.departureCity = departureCity;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public City getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(City arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
}

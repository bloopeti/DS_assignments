package model;

import javax.persistence.*;

@Entity
@Table(name = "RouteEntry")
public class RouteEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "Time")
    private String time;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "RouteCity", referencedColumnName = "Id")
    private City city;

    public RouteEntry() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}

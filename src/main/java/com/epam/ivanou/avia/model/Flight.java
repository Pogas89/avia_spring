package com.epam.ivanou.avia.model;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * JavaBean class of Flight entity
 */
@Entity
@Table(name = "flights")
@NamedQueries({
        @NamedQuery(name = Flight.DELETE, query = "DELETE FROM Flight f WHERE f.id=?1"),
        @NamedQuery(name = Flight.GET_ALL, query = "SELECT f FROM Flight f order by f.datetime")
})
public class Flight extends AbstractBaseEntity {

    public static final String DELETE = "Flight.delete";
    public static final String GET_ALL = "Flight.getAll";

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "departure", nullable = false)
    private String departure;

    @Column(name = "destination", nullable = false)
    private String destination;

    @Column(name = "datetime", nullable = false)
    private LocalDateTime datetime;

    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private FlightStatus status;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "crew_id")
    private Crew crew;

    public Flight() {
    }

    public Flight(Flight flight) {
        this.id = flight.getId();
        this.name = flight.getName();
        this.departure = flight.getDeparture();
        this.destination = flight.getDestination();
        this.datetime = flight.getDatetime();
        this.status = flight.getStatus();
        this.crew = flight.getCrew();
    }

    public Flight(Integer id, String name, String departure, String destination,
                  LocalDateTime datetime, FlightStatus status, Crew crew) {
        super(id);
        this.name = name;
        this.departure = departure;
        this.destination = destination;
        this.datetime = datetime;
        this.status = status;
        this.crew = crew;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    public FlightStatus getStatus() {
        return status;
    }

    public void setStatus(FlightStatus status) {
        this.status = status;
    }

    public Crew getCrew() {
        return crew;
    }

    public void setCrew(Crew crew) {
        this.crew = crew;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Flight)) {
            return false;
        }

        Flight flight = (Flight) obj;

        return getName().equals(flight.getName()) && getDeparture().equals(flight.getDeparture())
                && getDestination().equals(flight.getDestination()) && getDatetime().equals(flight.getDatetime())
                && getStatus() == flight.getStatus() && (getCrew() != null
                ? getCrew().equals(flight.getCrew()) : flight.getCrew() == null);
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = (31 * result) + getDeparture().hashCode();
        result = (31 * result) + getDestination().hashCode();
        result = (31 * result) + getDatetime().hashCode();
        result = (31 * result) + getStatus().hashCode();
        result = (31 * result) + ((getCrew() != null) ? getCrew().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", departure='" + departure + '\'' +
                ", destination='" + destination + '\'' +
                ", datetime=" + datetime +
                ", status=" + status +
                ", crew=" + crew +
                '}';
    }
}

package com.epam.ivanou.avia.model;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;

/**
 * JavaBean class of Flight entity
 */
public class Flight extends AbstractBaseEntity {
    private String name;

    private String departure;

    private String destination;

    private LocalDateTime datetime;

    private FlightStatus status;

    private Crew crew;

    public Flight() {
    }

    public Flight(Flight flight) {
        this.id = flight.getId();
        this.name = flight.getName();
        this.departure = flight.getDeparture();
        this.destination = flight.getDestination();
        this.datetime = datetime;
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

package com.epam.ivanou.avia.model;

import java.sql.Date;
import java.sql.Time;

/**
 * JavaBean class of Flight entity
 */
public class Flight extends AbstractBaseEntity {
    private String name;

    private String departure;

    private String destination;

    private Date date;

    private Time time;

    private FlightStatus status;

    private Crew crew;

    public Flight() {
    }

    public Flight(Flight flight) {
        this.id = flight.getId();
        this.name = flight.getName();
        this.departure = flight.getDeparture();
        this.destination = flight.getDestination();
        this.date = flight.getDate();
        this.time = flight.getTime();
        this.status = flight.getStatus();
        this.crew = flight.getCrew();
    }

    public Flight(Integer id, String name, String departure, String destination,
                  Date date, Time time, FlightStatus status, Crew crew) {
        super(id);
        this.name = name;
        this.departure = departure;
        this.destination = destination;
        this.date = date;
        this.time = time;
        this.status = status;
        this.crew = crew;
    }

    public Flight(String name, String departure, String destination,
                  Date date, Time time, FlightStatus status, Crew crew) {
        super(null);
        this.name = name;
        this.departure = departure;
        this.destination = destination;
        this.date = date;
        this.time = time;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
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
                ", date=" + date +
                ", time=" + time +
                ", status=" + status +
                ", crew=" + crew +
                '}';
    }
}

package com.epam.ivanou.avia.model;

import java.sql.Date;
import java.sql.Time;

/**
 * JavaBean class of Flight entity
 */
public class Flight {
    private Integer id;

    private String name;

    private String departure;

    private String destination;

    private Date date;

    private Time time;

    private FlightStatus status;

    private Crew crew;

    public Flight(Integer id, String name, String departure, String destination, Date date, Time time, FlightStatus status, Crew crew) {
        this.id = id;
        this.name = name;
        this.departure = departure;
        this.destination = destination;
        this.date = date;
        this.time = time;
        this.status = status;
        this.crew = crew;
    }

    public Flight(String name, String departure, String destination, Date date, Time time, FlightStatus status, Crew crew) {
        this(null,name,departure,destination,date,time,status,crew);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public boolean isNew(){
        return id==null;
    }
}

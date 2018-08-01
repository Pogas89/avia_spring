package com.epam.ivanou.avia.service;

import com.epam.ivanou.avia.model.Flight;
import com.epam.ivanou.avia.util.exception.NotFoundException;

import java.util.List;

public interface FlightService {
    Flight create(Flight flight);

    void update(Flight flight);

    void delete(int id) throws NotFoundException;

    Flight get(int id) throws NotFoundException;

    List<Flight> getAll();
}

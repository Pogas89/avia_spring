package com.epam.ivanou.avia.repository;


import com.epam.ivanou.avia.model.Flight;

import java.util.List;

public interface FlightRepository {
    Flight save(Flight flight);

    // false if not found
    boolean delete(int id);

    // null if not found
    Flight get(int id);

    List<Flight> getAll();
}

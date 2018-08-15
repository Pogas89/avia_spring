package com.epam.ivanou.avia.repository.jpa;

import com.epam.ivanou.avia.model.Flight;
import com.epam.ivanou.avia.repository.FlightRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class JpaFlightRepository implements FlightRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Flight save(Flight flight) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public Flight get(int id) {
        return null;
    }

    @Override
    public List<Flight> getAll() {
        return null;
    }
}

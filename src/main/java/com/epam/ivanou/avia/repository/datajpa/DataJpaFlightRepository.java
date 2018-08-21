package com.epam.ivanou.avia.repository.datajpa;

import com.epam.ivanou.avia.model.Flight;
import com.epam.ivanou.avia.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DataJpaFlightRepository implements FlightRepository {
    private static final Sort SORT_BY_DATETIME = new Sort(Sort.Direction.ASC, "datetime");

    @Autowired
    private CrudFlightRepository repository;

    @Override
    public Flight save(Flight flight) {
        return repository.save(flight);
    }

    @Override
    public boolean delete(int id) {
        return repository.delete(id) != 0;
    }

    @Override
    public Flight get(int id) {
        return repository.findById(id).get();
    }

    @Override
    public List<Flight> getAll() {
        return repository.findAll(SORT_BY_DATETIME);
    }
}

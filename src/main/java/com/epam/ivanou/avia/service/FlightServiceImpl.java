package com.epam.ivanou.avia.service;

import com.epam.ivanou.avia.model.Flight;
import com.epam.ivanou.avia.repository.FlightRepository;
import com.epam.ivanou.avia.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightRepository repository;

    @Override
    public Flight create(Flight flight) {
        return repository.save(flight);
    }

    @Override
    public void update(Flight flight) {
        repository.save(flight);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        repository.delete(id);
    }

    @Override
    public Flight get(int id) throws NotFoundException {
        return repository.get(id);
    }

    @Override
    public List<Flight> getAll() {
        return repository.getAll();
    }
}

package com.epam.ivanou.avia.service;

import com.epam.ivanou.avia.model.Flight;
import com.epam.ivanou.avia.repository.FlightRepository;
import com.epam.ivanou.avia.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.epam.ivanou.avia.util.ValidationUtil.checkNotFoundWithId;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightRepository flightRepository;

    @Override
    public Flight create(Flight flight) {
        return flightRepository.save(flight);
    }

    @Override
    public void update(Flight flight) {
        flightRepository.save(flight);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(flightRepository.delete(id),id);
    }

    @Override
    public Flight get(int id) throws NotFoundException {
        return checkNotFoundWithId(flightRepository.get(id),id);
    }

    @Override
    public List<Flight> getAll() {
        return flightRepository.getAll();
    }
}

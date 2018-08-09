package com.epam.ivanou.avia.service;

import com.epam.ivanou.avia.model.Flight;
import com.epam.ivanou.avia.repository.CrewRepository;
import com.epam.ivanou.avia.repository.FlightRepository;
import com.epam.ivanou.avia.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private CrewRepository crewRepository;

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
        flightRepository.delete(id);
    }

    @Override
    public Flight get(int id) throws NotFoundException {
        Flight flight = flightRepository.get(id);
//        if (flight.getCrew() != null)
//            flight.setCrew(crewRepository.get(flight.getCrew().getId()));
        return flight;
    }

    @Override
    public List<Flight> getAll() {
        List<Flight> flightList = flightRepository.getAll();
//        for (Flight flight : flightList) {
//            if (flight.getCrew().getId() != null)
//                flight.setCrew(crewRepository.get(flight.getCrew().getId()));
//        }
        return flightList;
    }
}

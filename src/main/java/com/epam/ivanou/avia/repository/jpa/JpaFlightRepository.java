package com.epam.ivanou.avia.repository.jpa;

import com.epam.ivanou.avia.model.Flight;
import com.epam.ivanou.avia.repository.FlightRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaFlightRepository implements FlightRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public Flight save(Flight flight) {
        if (flight.isNew()){
            em.persist(flight);
        } else {
            em.merge(flight);
        }
        return flight;
    }

    @Transactional
    @Override
    public boolean delete(int id) {
        return em.createNamedQuery(Flight.DELETE)
                .setParameter(1, id).executeUpdate() != 0;
    }

    @Override
    public Flight get(int id) {
        return em.find(Flight.class,id);
    }

    @Override
    public List<Flight> getAll() {
        return em.createNamedQuery(Flight.GET_ALL, Flight.class).getResultList();
    }
}

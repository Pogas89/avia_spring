package com.epam.ivanou.avia.repository.jpa;

import com.epam.ivanou.avia.model.Crew;
import com.epam.ivanou.avia.repository.CrewRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class JpaCrewRepository implements CrewRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Crew save(Crew crew) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public Crew get(int id) {
        return null;
    }

    @Override
    public List<Crew> getAll() {
        return null;
    }
}

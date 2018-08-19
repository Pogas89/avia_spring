package com.epam.ivanou.avia.repository.jpa;

import com.epam.ivanou.avia.model.Crew;
import com.epam.ivanou.avia.repository.CrewRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class JpaCrewRepository implements CrewRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Crew save(Crew crew) {
        if (crew.isNew()){
            em.persist(crew);
        } else {
            em.merge(crew);
        }
        return crew;
    }

    @Override
    public boolean delete(int id) {
        return em.createNamedQuery(Crew.DELETE).setParameter(1,id)
                .executeUpdate()!=0;
    }

    @Override
    public Crew get(int id) {
        return em.find(Crew.class,id);
    }

    @Override
    public List<Crew> getAll() {
        return em.createNamedQuery(Crew.All, Crew.class).getResultList();
    }
}

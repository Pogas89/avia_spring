package com.epam.ivanou.avia.repository.datajpa;

import com.epam.ivanou.avia.model.Crew;
import com.epam.ivanou.avia.repository.CrewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DataJpaCrewRepository implements CrewRepository {
    private static final Sort SORT_BY_NAME = new Sort(Sort.Direction.ASC, "name");

    @Autowired
    private CrudCrewRepository repository;

    @Override
    public Crew save(Crew crew) {
        return repository.save(crew);
    }

    @Override
    public boolean delete(int id) {
        return repository.delete(id) != 0;
    }

    @Override
    public Crew get(int id) {
        return repository.findById(id).get();
    }

    @Override
    public List<Crew> getAll() {
        return repository.findAll(SORT_BY_NAME);
    }
}

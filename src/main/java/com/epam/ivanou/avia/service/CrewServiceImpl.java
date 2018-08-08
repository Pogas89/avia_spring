package com.epam.ivanou.avia.service;

import com.epam.ivanou.avia.model.Crew;
import com.epam.ivanou.avia.repository.CrewRepository;
import com.epam.ivanou.avia.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrewServiceImpl implements CrewService {

    @Autowired
    private CrewRepository repository;

    @Override
    public Crew create(Crew crew) {
        return repository.save(crew);
    }

    @Override
    public void update(Crew crew) {
        repository.save(crew);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        repository.delete(id);
    }

    @Override
    public Crew get(int id) throws NotFoundException {
        return repository.get(id);
    }

    @Override
    public List<Crew> getAll() {
        return repository.getAll();
    }
}

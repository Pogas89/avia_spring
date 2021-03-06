package com.epam.ivanou.avia.service;

import com.epam.ivanou.avia.model.Crew;
import com.epam.ivanou.avia.repository.CrewRepository;
import com.epam.ivanou.avia.repository.UserRepository;
import com.epam.ivanou.avia.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.epam.ivanou.avia.util.ValidationUtil.checkNotFoundWithId;

@Service
public class CrewServiceImpl implements CrewService {

    @Autowired
    private CrewRepository crewRepository;

    @Override
    public Crew create(Crew crew) {
        return crewRepository.save(crew);
    }

    @Override
    public void update(Crew crew) {
        crewRepository.save(crew);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(crewRepository.delete(id),id);
    }

    @Override
    public Crew get(int id) throws NotFoundException {
        return checkNotFoundWithId(crewRepository.get(id),id);
    }

    @Override
    public List<Crew> getAll() {
        return crewRepository.getAll();
    }
}

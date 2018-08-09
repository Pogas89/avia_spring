package com.epam.ivanou.avia.service;

import com.epam.ivanou.avia.model.Crew;
import com.epam.ivanou.avia.model.User;
import com.epam.ivanou.avia.repository.CrewRepository;
import com.epam.ivanou.avia.repository.UserRepository;
import com.epam.ivanou.avia.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrewServiceImpl implements CrewService {

    @Autowired
    private CrewRepository crewRepository;

    @Autowired
    private UserRepository userRepository;

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
        crewRepository.delete(id);
    }

    @Override
    public Crew get(int id) throws NotFoundException {

        Crew crew = crewRepository.get(id);
        crew.setUser(userRepository.get(crew.getUser().getId()));
        return crew;
    }

    @Override
    public List<Crew> getAll() {
        List<Crew> crewList = crewRepository.getAll();
        for (Crew crew : crewList) {
            crew.setUser(userRepository.get(crew.getUser().getId()));
        }
        return crewList;
    }
}

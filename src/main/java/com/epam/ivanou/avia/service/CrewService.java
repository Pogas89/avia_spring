package com.epam.ivanou.avia.service;


import com.epam.ivanou.avia.model.Crew;
import com.epam.ivanou.avia.util.exception.NotFoundException;

import java.util.List;

public interface CrewService {
    Crew create(Crew crew);

    void update(Crew crew);

    void delete(int id) throws NotFoundException;

    Crew get(int id) throws NotFoundException;

    List<Crew> getAll();
}

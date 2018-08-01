package com.epam.ivanou.avia.repository;


import com.epam.ivanou.avia.model.Crew;

import java.util.List;

public interface CrewRepository {
    Crew save(Crew crew);

    // false if not found
    boolean delete(int id);

    // null if not found
    Crew get(int id);

    List<Crew> getAll();
}

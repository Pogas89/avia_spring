package com.epam.ivanou.avia.repository.datajpa;

import com.epam.ivanou.avia.model.Crew;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface CrudCrewRepository extends JpaRepository<Crew, Integer> {
    @Override
    @Transactional
    Crew save(Crew crew);

    @Override
    List<Crew> findAll(Sort sort);

    @Override
    Optional<Crew> findById(Integer integer);

    @Transactional
    @Modifying
    @Query(name = Crew.DELETE)
    int delete (int id);
}

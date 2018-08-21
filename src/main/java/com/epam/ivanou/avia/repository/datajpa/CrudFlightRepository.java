package com.epam.ivanou.avia.repository.datajpa;

import com.epam.ivanou.avia.model.Flight;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface CrudFlightRepository extends JpaRepository<Flight, Integer> {

    @Override
    @Transactional
    Flight save(Flight flight);

    @Transactional
    @Modifying
    @Query("DELETE FROM Flight f WHERE f.id=?1")
    int delete(int id);

    @Override
    Optional<Flight> findById(Integer integer);

    @Override
    List<Flight> findAll(Sort sort);
}

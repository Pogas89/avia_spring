package com.epam.ivanou.avia.repository.datajpa;

import com.epam.ivanou.avia.model.Staff;
import com.epam.ivanou.avia.model.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudStaffRepository extends JpaRepository<Staff, Integer> {
    @Override
    @Transactional
    Staff save(Staff staff);

    @Override
    Optional<Staff> findById(Integer integer);

    @Override
    List<Staff> findAll(Sort sort);

    @Transactional
    @Modifying
    @Query("DELETE FROM Staff s WHERE s.id=?1")
    int delete(int id);

    Staff getByLastname (String lastname);
}

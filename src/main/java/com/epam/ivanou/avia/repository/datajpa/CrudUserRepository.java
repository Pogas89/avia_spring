package com.epam.ivanou.avia.repository.datajpa;

import com.epam.ivanou.avia.model.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudUserRepository extends JpaRepository<User, Integer> {
    @Override
    @Transactional
    User save(User user);

    @Transactional
    @Modifying
    @Query("DELETE FROM User u WHERE u.id=?1")
    int delete(int id);

    @Override
    Optional<User> findById(Integer integer);

    @Override
    List<User> findAll(Sort sort);

    User findUserByEmail(String email);
}

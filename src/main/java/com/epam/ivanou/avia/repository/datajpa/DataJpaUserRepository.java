package com.epam.ivanou.avia.repository.datajpa;

import com.epam.ivanou.avia.model.User;
import com.epam.ivanou.avia.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DataJpaUserRepository implements UserRepository {
    private static final Sort SORT_EMAIL = new Sort("email");

    @Autowired
    CrudUserRepository repository;

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public boolean delete(int id) {
        return repository.delete(id) != 0;
    }

    @Override
    public User get(int id) {
        return repository.findById(id).get();
    }

    @Override
    public User getByEmail(String login) {
        return repository.getByEmail(login);
    }

    @Override
    public List<User> getAll() {
        return repository.findAll(SORT_EMAIL);
    }
}

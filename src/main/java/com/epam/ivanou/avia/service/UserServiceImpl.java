package com.epam.ivanou.avia.service;

import com.epam.ivanou.avia.model.User;
import com.epam.ivanou.avia.repository.UserRepository;
import com.epam.ivanou.avia.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository repository;

    @Override
    public User create(User user) {
        return repository.save(user);
    }

    @Override
    public void update(User user) {
        repository.save(user);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        repository.delete(id);
    }

    @Override
    public User get(int id) throws NotFoundException {
        return repository.get(id);
    }

    @Override
    public User getByLogin(String login) throws NotFoundException {
        return repository.getByLogin(login);
    }

    @Override
    public List<User> getAll() {
        return repository.getAll();
    }
}

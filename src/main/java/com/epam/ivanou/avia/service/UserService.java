package com.epam.ivanou.avia.service;

import com.epam.ivanou.avia.model.User;
import com.epam.ivanou.avia.util.exception.NotFoundException;

import java.util.List;

public interface UserService {
    User create(User user);

    void update(User user);

    void delete(int id) throws NotFoundException;

    User get(int id) throws NotFoundException;

    User getByEmail(String email) throws NotFoundException;

    List<User> getAll();
}

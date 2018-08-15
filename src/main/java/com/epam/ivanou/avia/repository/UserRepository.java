package com.epam.ivanou.avia.repository;


import com.epam.ivanou.avia.model.User;

import java.util.List;

public interface UserRepository {
    User save(User user);

    //false if not found
    boolean delete(int id);

    //null if not found
    User get(int id);

    //null if not found
    User getByEmail(String login);

    List<User> getAll();
}

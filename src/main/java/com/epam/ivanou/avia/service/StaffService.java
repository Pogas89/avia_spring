package com.epam.ivanou.avia.service;

import com.epam.ivanou.avia.model.Staff;
import com.epam.ivanou.avia.util.exception.NotFoundException;

import java.util.List;

public interface StaffService {
    Staff create(Staff staff);

    void update(Staff staff);

    void delete(Integer id) throws NotFoundException;

    Staff get(int id) throws NotFoundException;

    Staff getByLastname(String lastName) throws NotFoundException;

    List<Staff> getAll();
}

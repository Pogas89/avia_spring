package com.epam.ivanou.avia.repository;


import com.epam.ivanou.avia.model.Staff;

import java.util.List;

public interface StaffRepository {
    Staff save(Staff staff);

    // false if not found
    boolean delete(int id);

    // null if not found
    Staff get(int id);

    // null if not found
    Staff getByLastname(String lastname);

    List<Staff> getAll();
}

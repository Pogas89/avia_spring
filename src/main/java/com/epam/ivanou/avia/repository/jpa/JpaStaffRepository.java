package com.epam.ivanou.avia.repository.jpa;

import com.epam.ivanou.avia.model.Staff;
import com.epam.ivanou.avia.repository.StaffRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class JpaStaffRepository implements StaffRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Staff save(Staff staff) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public Staff get(int id) {
        return null;
    }

    @Override
    public Staff getByLastname(String lastname) {
        return null;
    }

    @Override
    public List<Staff> getAll() {
        return null;
    }
}

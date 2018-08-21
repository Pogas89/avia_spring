package com.epam.ivanou.avia.repository.datajpa;

import com.epam.ivanou.avia.model.Staff;
import com.epam.ivanou.avia.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DataJpaStaffRepository implements StaffRepository {
    private static final Sort SORT_BY_LASTNAME_FIRSTNAME =
            new Sort(Sort.Direction.ASC,"lastname", "firstname");

    @Autowired
    private CrudStaffRepository repository;

    @Override
    public Staff save(Staff staff) {
        return repository.save(staff);
    }

    @Override
    public boolean delete(int id) {
        return repository.delete(id) != 0;
    }

    @Override
    public Staff get(int id) {
        return repository.findById(id).get();
    }

    @Override
    public Staff getByLastname(String lastname) {
        return repository.getByLastname(lastname);
    }

    @Override
    public List<Staff> getAll() {
        return repository.findAll(SORT_BY_LASTNAME_FIRSTNAME);
    }
}

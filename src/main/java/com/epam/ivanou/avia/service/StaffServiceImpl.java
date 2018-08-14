package com.epam.ivanou.avia.service;

import com.epam.ivanou.avia.model.Staff;
import com.epam.ivanou.avia.repository.StaffRepository;
import com.epam.ivanou.avia.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.epam.ivanou.avia.util.ValidationUtil.*;

@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffRepository repository;

    @Override
    public Staff create(Staff staff) {
        return repository.save(staff);
    }

    @Override
    public void update(Staff staff) {
        repository.save(staff);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id),id);
    }

    @Override
    public Staff get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id),id);
    }

    @Override
    public Staff getByLastname(String lastName) throws NotFoundException {
        return repository.getByLastname(lastName);
    }

    @Override
    public List<Staff> getAll() {
        return repository.getAll();
    }
}

package com.epam.ivanou.avia.service;

import com.epam.ivanou.avia.model.Staff;
import com.epam.ivanou.avia.repository.StaffRepository;
import com.epam.ivanou.avia.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public void delete(Integer id) throws NotFoundException {
        repository.delete(id);
    }

    @Override
    public Staff get(int id) throws NotFoundException {
        return repository.get(id);
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

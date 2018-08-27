package com.epam.ivanou.avia.repository.jpa;

import com.epam.ivanou.avia.model.Staff;
import com.epam.ivanou.avia.repository.StaffRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaStaffRepository implements StaffRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public Staff save(Staff staff) {
        if (staff.isNew()) {
            em.persist(staff);
        } else {
            em.merge(staff);
        }
        return staff;
    }

    @Transactional
    @Override
    public boolean delete(int id) {
        return em.createNamedQuery(Staff.DELETE)
                .setParameter(1, id).executeUpdate() != 0;
    }

    @Override
    public Staff get(int id) {
        return em.find(Staff.class,id);
    }

    @Override
    public Staff getByLastname(String lastname) {
        return em.createNamedQuery(Staff.BY_LASTNAME, Staff.class)
                .setParameter(1,lastname).getSingleResult();
    }

    @Override
    public List<Staff> getAll() {
        return em.createNamedQuery(Staff.GET_ALL,Staff.class).getResultList();
    }
}

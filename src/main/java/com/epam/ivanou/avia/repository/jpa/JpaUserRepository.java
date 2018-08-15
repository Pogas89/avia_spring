package com.epam.ivanou.avia.repository.jpa;

import com.epam.ivanou.avia.model.User;
import com.epam.ivanou.avia.repository.UserRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class JpaUserRepository implements UserRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public User save(User user) {
        if (user.isNew()) {
            em.persist(user);
        } else {
            em.merge(user);
        }
        return user;
    }

    @Override
    public boolean delete(int id) {
//        TypedQuery<User> query = em.createQuery("DELETE from User u where u.id=:id", User.class);
//        return query.setParameter("id", id).executeUpdate() != 0;
        return em.createNamedQuery(User.DELETE)
                .setParameter(1, id).executeUpdate() != 0;
    }

    @Override
    public User get(int id) {
        System.out.println(User.class);
        return em.find(User.class, id);
    }

    @Override
    public User getByEmail(String email) {
        return em.createNamedQuery(User.BY_EMAIL, User.class)
                .setParameter("email", email).getSingleResult();
    }

    @Override
    public List<User> getAll() {
        return em.createNamedQuery(User.All, User.class).getResultList();
    }
}

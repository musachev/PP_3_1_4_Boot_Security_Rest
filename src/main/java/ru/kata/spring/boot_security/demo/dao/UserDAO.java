package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;


@Repository
public class UserDAO {
    @PersistenceContext()
    private EntityManager entityManager;

    @Transactional
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Transactional
    public void updateUser(User updateUser) {
        entityManager.merge(updateUser);
    }

    @Transactional
    public void removeUserById(int id) {
        User user = entityManager.find(User.class, id);
        if (user != null) {
            entityManager.remove(user);
        }
    }

    public List<User> getAllUsers() {
        return entityManager.createQuery("select u from User u").getResultList();
    }

    public User getUserById(int id) {
        return entityManager.find(User.class, id);
    }

    public User getUserByUsername(String username) {
        TypedQuery<User> q = (entityManager.createQuery("select u from User u " +
                "join fetch u.roles where u.username = :username", User.class));
        q.setParameter("username", username);
        return q.getResultList().stream().findFirst().orElse(null);
    }
}

package ru.sberbank.bankapi.repo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.sberbank.bankapi.domain.User;
import ru.sberbank.bankapi.repo.UserRepo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Optional;

@Repository
public class UserRepoImpl implements UserRepo {
/*
    private final EntityManager entityManager;

    @Autowired
    public UserRepoImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public Optional<User> findByUserName(String userName) {
        entityManager.getTransaction().begin();
        Optional<User> user = entityManager.createNamedQuery(
                "UserFindByUserName", User.class)
                .setParameter("username", userName)
                .getResultStream()
                .findAny();
        entityManager.getTransaction().commit();
        return user;
    }

    @Override
    public void save(User user) {
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteAll() {
        entityManager.getTransaction().begin();
        entityManager.createQuery("DELETE FROM User").executeUpdate();
        entityManager.getTransaction().commit();
    }
 */
}

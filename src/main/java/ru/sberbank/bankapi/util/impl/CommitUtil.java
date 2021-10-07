package ru.sberbank.bankapi.util.impl;

import ru.sberbank.bankapi.repo.CommitAction;

import javax.persistence.EntityManager;

public class CommitUtil {
    private final EntityManager entityManager;

    public CommitUtil(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void commit(CommitAction commitAction){
        entityManager.getTransaction().begin();
        try {
            commitAction.make();
        } catch (Exception e){
            entityManager.getTransaction().rollback();
            throw e;
        }
        entityManager.getTransaction().commit();
    }
}

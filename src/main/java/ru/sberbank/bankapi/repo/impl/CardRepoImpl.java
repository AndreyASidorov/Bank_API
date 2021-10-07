package ru.sberbank.bankapi.repo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.sberbank.bankapi.domain.Account;
import ru.sberbank.bankapi.domain.Card;
import ru.sberbank.bankapi.repo.AccountRepo;
import ru.sberbank.bankapi.repo.CardRepo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Repository
public class CardRepoImpl implements CardRepo {

    private final EntityManager entityManager;
    AccountRepo accountRepo;

    @Autowired
    public CardRepoImpl(EntityManagerFactory entityManagerFactory, AccountRepo accountRepo) {
        this.entityManager = entityManagerFactory.createEntityManager();
        this.accountRepo = accountRepo;
    }

    /**
     * save new card in database
     * @param card - new card
     * @return - new card
     */
    @Override
    public Card saveNewCard(Card card) {
        entityManager.getTransaction().begin();
        entityManager.persist(card);
        entityManager.getTransaction().commit();
        return card;
    }

    /**
     * get all cards by account in database from database
     * @param account - account
     * @return - list of cards
     */
    @Override
    public List<Card> getAllCardsByAccount(Account account) {
        entityManager.getTransaction().begin();
        List<Card> cards = entityManager.createNamedQuery(
                        "getAllCardsByAccount", Card.class)
                .setParameter("account", account)
                .getResultList();
        entityManager.getTransaction().commit();
        return cards;
    }

    /**
     * delete all cards in database
     */
    @Override
    public void deleteAll() {
        entityManager.getTransaction().begin();
        entityManager.createNativeQuery("DELETE FROM CARD").executeUpdate();
        entityManager.createNativeQuery("ALTER TABLE CARD ALTER COLUMN ID RESTART WITH 1").executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.clear();
    }
}

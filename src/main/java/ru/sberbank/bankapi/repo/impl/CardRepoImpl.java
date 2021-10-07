package ru.sberbank.bankapi.repo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.sberbank.bankapi.domain.Account;
import ru.sberbank.bankapi.domain.Card;
import ru.sberbank.bankapi.repo.AccountRepo;
import ru.sberbank.bankapi.repo.CardRepo;
import ru.sberbank.bankapi.util.impl.CommitUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

@Repository
public class CardRepoImpl implements CardRepo {

    private final EntityManager entityManager;
    private final AccountRepo accountRepo;
    private final CommitUtil commitUtil;

    @Autowired
    public CardRepoImpl(EntityManagerFactory entityManagerFactory, AccountRepo accountRepo) {
        this.entityManager = entityManagerFactory.createEntityManager();
        this.commitUtil = new CommitUtil(entityManager);
        this.accountRepo = accountRepo;
    }

    /**
     * save new card in database
     * @param card - new card
     * @return - new card
     */
    @Override
    public Card saveNewCard(Card card) {
        commitUtil.commit(() -> entityManager.persist(card));
        return card;
    }

    /**
     * get all cards by account in database from database
     * @param account - account
     * @return - list of cards
     */
    @Override
    public List<Card> getAllCardsByAccount(Account account) {
        Query query = entityManager.createNamedQuery(
                        "getAllCardsByAccount", Card.class)
                .setParameter("account", account);
        List<Card> cards = query.getResultList();
        return cards;
    }

    /**
     * delete all cards in database
     */
    @Override
    public void deleteAll() {
        entityManager.clear();
        commitUtil.commit(() -> {
            entityManager.createNativeQuery("DELETE FROM card").executeUpdate();
            entityManager.createNativeQuery("ALTER TABLE card ALTER COLUMN ID RESTART WITH 1").executeUpdate();
        });
    }
}

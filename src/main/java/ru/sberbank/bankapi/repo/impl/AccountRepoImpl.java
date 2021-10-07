package ru.sberbank.bankapi.repo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.sberbank.bankapi.domain.Account;
import ru.sberbank.bankapi.repo.AccountRepo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Optional;

@Repository
public class AccountRepoImpl implements AccountRepo {

    private final EntityManager entityManager;

    @Autowired
    public AccountRepoImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    /**
     * search in bd by number of account
     * @param number - number of account
     * @return account entity
     */
    @Override
    public Optional<Account> findByNumber(String number) {
        Optional<Account> account = entityManager.createNamedQuery(
                        "AccountFindByNumber", Account.class)
                .setParameter("number", number)
                .getResultStream()
                .findAny();
        return account;
    }

    /**
     * install new balance of account entity in the database
     * @param account - entity with new balance
     * @return - success
     */
    @Override
    public Boolean setMoneyToAccount(Account account) {
        entityManager.getTransaction().begin();
        //entityManager.persist(account);
        entityManager.createNamedQuery("depositMoneyToAccount")
                .setParameter("balance", account.getBalance())
                .setParameter("id", account.getId())
                .executeUpdate();
        entityManager.getTransaction().commit();
        return true;
    }
}

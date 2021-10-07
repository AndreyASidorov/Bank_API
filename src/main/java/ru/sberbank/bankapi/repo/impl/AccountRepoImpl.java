package ru.sberbank.bankapi.repo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.sberbank.bankapi.domain.Account;
import ru.sberbank.bankapi.repo.AccountRepo;
import ru.sberbank.bankapi.util.impl.CommitUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.LockModeType;
import javax.persistence.Query;
import java.util.Optional;

@Repository
public class AccountRepoImpl implements AccountRepo {

    private final EntityManager entityManager;
    private final CommitUtil commitUtil;

    @Autowired
    public AccountRepoImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
        this.commitUtil = new CommitUtil(entityManager);
    }

    /**
     * search in bd by number of account
     *
     * @param number - number of account
     * @return account entity
     */
    @Override
    public Optional<Account> findByNumber(String number) {
        Query query = entityManager.createNamedQuery(
                "AccountFindByNumber", Account.class)
                .setParameter("number", number);
        query.setLockMode(LockModeType.OPTIMISTIC_FORCE_INCREMENT);
        Optional<Account> account = query
                .getResultStream()
                .findAny();
        return account;
    }

    /**
     * install new balance of account entity in the database
     *
     * @param account - entity with new balance
     * @return - success
     */
    @Override
    public Boolean setMoneyToAccount(Account account) {
        Query query = entityManager.createNamedQuery("depositMoneyToAccount")
                .setParameter("balance", account.getBalance())
                .setParameter("id", account.getId());
        query.executeUpdate();
        return true;
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}

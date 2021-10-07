package ru.sberbank.bankapi.repo;

import ru.sberbank.bankapi.domain.Account;

import java.util.Optional;

public interface AccountRepo {
    Optional<Account> findByNumber (String number);
    Boolean setMoneyToAccount(Account account);
}

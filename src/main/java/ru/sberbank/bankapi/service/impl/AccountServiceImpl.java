package ru.sberbank.bankapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sberbank.bankapi.domain.Account;
import ru.sberbank.bankapi.dto.DepositMoneyDto;
import ru.sberbank.bankapi.exception.AccountDoesNotExistsException;
import ru.sberbank.bankapi.exception.AccountWrongLengthException;
import ru.sberbank.bankapi.exception.NotEnoughMoneyException;
import ru.sberbank.bankapi.repo.AccountRepo;
import ru.sberbank.bankapi.service.AccountService;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

@Service
public class AccountServiceImpl implements AccountService {

    AccountRepo accountRepo;

    @Autowired
    AccountServiceImpl(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    /**
     * deposit money to account
     * @param depositMoneyDto model with number of account and money
     * @return new balance of account
     */
    @Override
    public BigDecimal depositMoneyToAccountByCloud(DepositMoneyDto depositMoneyDto) {
        if (depositMoneyDto.getNumberAccount().length() != 10) {
            throw new AccountWrongLengthException();
        }
        Account account = accountRepo
                .findByNumber(depositMoneyDto.getNumberAccount())
                .orElseThrow(AccountDoesNotExistsException::new);
        if (depositMoneyDto
                .getMoney()
                .add(account.getBalance())
                .compareTo(BigDecimal.ZERO) < 0) {
            throw new NotEnoughMoneyException();
        }
        account.setBalance(account
                .getBalance()
                .add(depositMoneyDto.getMoney()));
        accountRepo.setMoneyToAccount(account);
        return account.getBalance();
    }

    /**
     * check balance of account
     * @param number - number of account
     * @return - balance of account
     */
    @Override
    public BigDecimal checkBalance(String number) {
        if (number.length() != 10) {
            throw new AccountWrongLengthException();
        }
        return accountRepo
                .findByNumber(number)
                .orElseThrow(AccountDoesNotExistsException::new)
                .getBalance();
    }
}

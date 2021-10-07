package ru.sberbank.bankapi.service;

import ru.sberbank.bankapi.dto.DepositMoneyDto;

import java.math.BigDecimal;

public interface AccountService {
    BigDecimal depositMoneyToAccountByCloud (DepositMoneyDto depositMoneyDto);
    BigDecimal checkBalance(String number);
}

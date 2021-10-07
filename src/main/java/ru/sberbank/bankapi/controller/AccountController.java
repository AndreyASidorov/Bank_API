package ru.sberbank.bankapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.sberbank.bankapi.dto.DepositMoneyDto;
import ru.sberbank.bankapi.service.AccountService;

import java.math.BigDecimal;

@RestController
public class AccountController {

    AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/depositMoney")
    public BigDecimal depositMoneyToAccountByCloud(@RequestBody DepositMoneyDto depositMoneyDto) {
        return accountService.depositMoneyToAccountByCloud(depositMoneyDto);
    }

    @GetMapping("/checkBalance")
    public BigDecimal checkBalance(@RequestParam String number) {
        return accountService.checkBalance(number);
    }
}

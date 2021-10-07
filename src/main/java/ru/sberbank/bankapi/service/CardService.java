package ru.sberbank.bankapi.service;

import ru.sberbank.bankapi.domain.Card;
import ru.sberbank.bankapi.dto.AccountNumberDto;
import ru.sberbank.bankapi.dto.CardDto;

import java.util.List;

public interface CardService {
    CardDto createNewCard(AccountNumberDto accountNumberDto);
    List<CardDto> getAllCardsByAccount (String number);
}

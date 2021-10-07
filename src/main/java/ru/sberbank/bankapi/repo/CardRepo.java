package ru.sberbank.bankapi.repo;

import ru.sberbank.bankapi.domain.Account;
import ru.sberbank.bankapi.domain.Card;

import java.util.List;

public interface CardRepo {
    Card saveNewCard(Card card);
    List<Card> getAllCardsByAccount (Account account);
    void deleteAll();
}

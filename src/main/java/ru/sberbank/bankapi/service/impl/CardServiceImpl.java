package ru.sberbank.bankapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sberbank.bankapi.domain.Card;
import ru.sberbank.bankapi.dto.AccountNumberDto;
import ru.sberbank.bankapi.dto.CardDto;
import ru.sberbank.bankapi.exception.AccountDoesNotExistsException;
import ru.sberbank.bankapi.exception.AccountWrongLengthException;
import ru.sberbank.bankapi.repo.AccountRepo;
import ru.sberbank.bankapi.repo.CardRepo;
import ru.sberbank.bankapi.service.CardService;
import ru.sberbank.bankapi.util.CardNumberGenerator;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardServiceImpl implements CardService {

    AccountRepo accountRepo;
    CardRepo cardRepo;
    CardNumberGenerator cardNumberGenerator;

    @Autowired
    CardServiceImpl(AccountRepo accountRepo, CardRepo cardRepo, CardNumberGenerator cardNumberGenerator) {
        this.accountRepo = accountRepo;
        this.cardRepo = cardRepo;
        this.cardNumberGenerator = cardNumberGenerator;
    }

    /**
     * create new card by account number
     * @param accountNumberDto - model with number of account
     * @return - model with formatted number of new card and account number
     */
    @Override
    public CardDto createNewCard(AccountNumberDto accountNumberDto) {
        if (accountNumberDto.getNumber().length() != 10) {
            throw new AccountWrongLengthException();
        }
        return cardToDto(cardRepo.saveNewCard(
                Card.builder()
                        .account(accountRepo
                                .findByNumber(
                                        accountNumberDto.getNumber()).orElseThrow(AccountDoesNotExistsException::new)
                        )
                        .number(cardNumberGenerator.generate())
                        .build()
        ));
    }

    /**
     * get all cards by account
     * @param number - number of account
     * @return - list of models card by account
     */
    @Override
    public List<CardDto> getAllCardsByAccount(String number) {
        return cardToDto(cardRepo.getAllCardsByAccount(
                accountRepo.findByNumber(number).orElseThrow(AccountDoesNotExistsException::new)));
    }

    /**
     * converting a lis of cards to list models of cards
     * @param cards - list of cards
     * @return - list of models
     */
    private List<CardDto> cardToDto(List<Card> cards) {
        List<CardDto> result = new ArrayList<>();
        for (Card card : cards) {
            result.add(
                    new CardDto(
                            card.getId(),
                            card.getNumber(),
                            new AccountNumberDto(card.getAccount().getNumber())
                    )
            );
        }
        return result;
    }

    /**
     * converting a card to model of card
     * @param card - entity
     * @return - model of card
     */
    private CardDto cardToDto(Card card) {
        return new CardDto(
                card.getId(),
                card.getNumber(),
                new AccountNumberDto(card.getAccount().getNumber())
        );
    }
}

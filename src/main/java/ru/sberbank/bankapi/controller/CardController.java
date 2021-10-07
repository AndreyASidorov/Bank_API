package ru.sberbank.bankapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.sberbank.bankapi.dto.AccountNumberDto;
import ru.sberbank.bankapi.dto.CardDto;
import ru.sberbank.bankapi.service.CardService;

import java.util.List;

@RestController
class CardController {

    CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping("/newCard")
    public CardDto createNewCard(@RequestBody AccountNumberDto accountNumberDto) {
        return cardService.createNewCard(accountNumberDto);
    }

    @GetMapping("/cards")
    public List<CardDto> getAllCardsByAccount(@RequestParam String number) {
        return cardService.getAllCardsByAccount(number);
    }

}



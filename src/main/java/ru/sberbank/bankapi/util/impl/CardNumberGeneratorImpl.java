package ru.sberbank.bankapi.util.impl;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.sberbank.bankapi.util.CardNumberGenerator;

/**
 * generate new number of cards
 */
@Service
public class CardNumberGeneratorImpl implements CardNumberGenerator {
    public String generate() {
        StringBuilder randomCardNumber = new StringBuilder("4276");
        for (int i = 0; i < 12; i++) {
            randomCardNumber.append((int) (Math.random() * 10));
        }
        return randomCardNumber.toString();
    }
}

package ru.sberbank.bankapi.Util;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.sberbank.bankapi.util.CardNumberGenerator;

@Service
@Primary
class TestCardNumberGeneratorImpl implements CardNumberGenerator {
    public String generate() {
        return "4276111122223333";
    }
}

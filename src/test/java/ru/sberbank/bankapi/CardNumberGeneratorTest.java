package ru.sberbank.bankapi;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.sberbank.bankapi.util.impl.CardNumberGeneratorImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CardNumberGeneratorTest {
    @Test
    public void testCardNumberGenerator() {
        CardNumberGeneratorImpl cardNumberGenerator = new CardNumberGeneratorImpl();
        String res1 = cardNumberGenerator.generate();
        String res2 = cardNumberGenerator.generate();
        Assert.assertTrue(res1.length() == 16);
        Assert.assertTrue(res1.indexOf("4276") == 0);
        Assert.assertNotEquals(res1, res2);
    }

}

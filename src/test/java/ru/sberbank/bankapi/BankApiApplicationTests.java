package ru.sberbank.bankapi;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BankApiApplicationTests {

    @Test
    void contextLoads() {
        Assert.assertTrue(1 == 1);
    }

}

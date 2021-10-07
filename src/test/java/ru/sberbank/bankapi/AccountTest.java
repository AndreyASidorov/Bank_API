package ru.sberbank.bankapi;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import ru.sberbank.bankapi.domain.Account;
import ru.sberbank.bankapi.dto.DepositMoneyDto;
import ru.sberbank.bankapi.repo.AccountRepo;
import ru.sberbank.bankapi.util.impl.CommitUtil;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    AccountRepo accountRepo;

    @LocalServerPort
    protected int port;

    @Before
    public void beforeEach() {
        Account account = accountRepo.findByNumber("0000000001").get();
        account.setBalance(BigDecimal.valueOf(1111.11));
        CommitUtil commitUtil = new CommitUtil(accountRepo.getEntityManager());
        commitUtil.commit(() -> accountRepo.setMoneyToAccount(account));
    }

    @Test
    public void testDepositMoneyToAccountByCloud(){
        DepositMoneyDto depositMoneyDto = new DepositMoneyDto("0000000001", BigDecimal.valueOf(1111.11));
        //RestTemplate restTemplate = new RestTemplate();
        BigDecimal bigDecimal = restTemplate.postForEntity("http://localhost:" + port + "/depositMoney",depositMoneyDto,BigDecimal.class).getBody();
        Assert.assertTrue(bigDecimal != null);
        Assert.assertEquals(bigDecimal, BigDecimal.valueOf(2222.22));
    }

    @Test
    public void testNegativeDepositMoneyToAccountByCloud() {
        DepositMoneyDto depositMoneyDto = new DepositMoneyDto("0000000001", BigDecimal.valueOf(-11111.11));
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:" + port + "/depositMoney",depositMoneyDto,String.class);
        Assert.assertTrue(responseEntity.getStatusCode().equals(HttpStatus.BAD_REQUEST));
        Assert.assertTrue(responseEntity.getBody().equals("Not enough money"));
    }

    @Test
    public void testCheckBalance(){
        //RestTemplate restTemplate = new RestTemplate();
        BigDecimal bigDecimal = restTemplate.getForEntity("http://localhost:" + port + "/checkBalance" + "?number=0000000001",BigDecimal.class).getBody();
        assert bigDecimal != null;
        Assert.assertEquals(bigDecimal, BigDecimal.valueOf(1111.11));
    }

    @Test
    public void testInvalidNumberCheckBalance(){
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:" + port + "/checkBalance" + "?number=1",String.class);
        Assert.assertTrue(responseEntity.getStatusCode().equals(HttpStatus.BAD_REQUEST));
        Assert.assertTrue(responseEntity.getBody().equals("Account number length should be 10"));
    }
}
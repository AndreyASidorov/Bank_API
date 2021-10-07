package ru.sberbank.bankapi;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import ru.sberbank.bankapi.domain.Card;
import ru.sberbank.bankapi.dto.AccountNumberDto;
import ru.sberbank.bankapi.dto.CardDto;
import ru.sberbank.bankapi.repo.AccountRepo;
import ru.sberbank.bankapi.repo.CardRepo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CardTests {
    @Autowired
    CardRepo cardRepo;

    @Autowired
    AccountRepo accountRepo;

    @Autowired
    TestRestTemplate restTemplate;

    @LocalServerPort
    protected int port;

    @Before
    public void beforeEach() {
        cardRepo.deleteAll();
        cardRepo.saveNewCard(Card.builder()
                .account(accountRepo.findByNumber("0000000001").get())
                .number("4276000011112222")
                .build());
        cardRepo.saveNewCard(Card.builder()
                .account(accountRepo.findByNumber("0000000001").get())
                .number("4276999988887777")
                .build());


    }

    @Test
    public void testCreateNewCard() {
        AccountNumberDto accountNumberDto = new AccountNumberDto("0000000001");
        CardDto card = restTemplate.postForEntity("http://localhost:" + port + "/newCard", accountNumberDto, CardDto.class).getBody();
        Assert.assertTrue(card.getNumber().equals("4276 1111 2222 3333")
                && card.getAccount().getNumber().equals(accountNumberDto.getNumber()));
    }

    @Test
    public void testNotExistAccountToCreateNewCard() {
        AccountNumberDto accountNumberDto = new AccountNumberDto("1234567890");
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:" + port + "/newCard", accountNumberDto, String.class);
        Assert.assertTrue(responseEntity.getStatusCode().equals(HttpStatus.BAD_REQUEST));
        Assert.assertTrue(responseEntity.getBody().equals("Account with this number is not exists"));
    }

    @Test
    public void testGetAllCardsByAccount() {
        CardDto[] cards = restTemplate.getForEntity("http://localhost:" + port + "/cards" + "?number=0000000001", CardDto[].class).getBody();
        CardDto[] cardsActual = {
                CardDto.builder()
                        .id(1L)
                        .account(new AccountNumberDto(accountRepo.findByNumber("0000000001").get().getNumber()))
                        .number("4276 0000 1111 2222")
                        .build(),
                CardDto.builder()
                        .id(2L)
                        .account(new AccountNumberDto(accountRepo.findByNumber("0000000001").get().getNumber()))
                        .number("4276 9999 8888 7777")
                        .build()
        };
        Assert.assertArrayEquals(cards, cardsActual);
        /*Assert.assertArrayEquals(cards, cardRepo
                .getAllCardsByAccount(
                        accountRepo
                                .findByNumber("0000000001")
                                .get())
                .stream()
                .map(card -> new CardDto(
                        card.getId(),
                        card.getNumber(),
                        new AccountNumberDto(card.getAccount().getNumber())))
                .toArray());

         */
    }
}

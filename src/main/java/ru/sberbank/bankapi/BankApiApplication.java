package ru.sberbank.bankapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@SpringBootApplication
//@EnableConfigurationProperties
public class BankApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankApiApplication.class, args);
    }
}

package ru.sberbank.bankapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Configuration
public class DbConfig {
    @Bean
    @Scope("singleton")
    public EntityManagerFactory entityManagerFactory() {
        return Persistence.createEntityManagerFactory("bank");
    }
}

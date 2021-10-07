package ru.sberbank.bankapi.exception;

public class AccountDoesNotExistsException extends RuntimeException {
    public AccountDoesNotExistsException() {
        super("Account with this number is not exists");
    }
}

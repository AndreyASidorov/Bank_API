package ru.sberbank.bankapi.exception;

public class AccountWrongLengthException extends RuntimeException {
    public AccountWrongLengthException() {
        super("Account number length should be 10");
    }
}

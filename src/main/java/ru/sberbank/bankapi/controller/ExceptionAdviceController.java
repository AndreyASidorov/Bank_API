package ru.sberbank.bankapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import ru.sberbank.bankapi.exception.AccountDoesNotExistsException;
import ru.sberbank.bankapi.exception.AccountWrongLengthException;
import ru.sberbank.bankapi.exception.NotEnoughMoneyException;

@ControllerAdvice
public class ExceptionAdviceController {

    @ExceptionHandler(AccountDoesNotExistsException.class)
    public ResponseEntity<String> handleException(AccountDoesNotExistsException e) {
        return new ResponseEntity<>("Account with this number is not exists", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccountWrongLengthException.class)
    public ResponseEntity<String> handleException(AccountWrongLengthException e) {
        return new ResponseEntity<>("Account number length should be 10", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotEnoughMoneyException.class)
    public ResponseEntity<String> handleException(NotEnoughMoneyException e) {
        return new ResponseEntity<>("Not enough money", HttpStatus.BAD_REQUEST);
    }
}

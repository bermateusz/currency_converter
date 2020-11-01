package com.bereda.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(CurrencyExchangeRateDoesNotExistException.class)
    public ResponseEntity<?> handleCurrencyExchangeRateDoesNotExistException(CurrencyExchangeRateDoesNotExistException exception, WebRequest request) {
        com.bereda.exception.ErrorDetails errorDetails = new com.bereda.exception.ErrorDetails(LocalDateTime.now(), exception.getMessage(), request.getDescription(false), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

}

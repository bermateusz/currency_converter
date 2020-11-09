package com.bereda.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ExceptionHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @org.springframework.web.bind.annotation.ExceptionHandler(CurrencyExchangeRateDoesNotExistException.class)
    public ResponseEntity<ErrorDetails> handleCurrencyExchangeRateDoesNotExistException(CurrencyExchangeRateDoesNotExistException exception, WebRequest request) {
        return buildResponseEntity(new ErrorDetails(exception.getMessage(), request.getDescription(false), HttpStatus.NOT_FOUND));
    }

    private ResponseEntity<ErrorDetails> buildResponseEntity(ErrorDetails errorDetails) {
        return new ResponseEntity<>(errorDetails, errorDetails.getStatus());
    }

}

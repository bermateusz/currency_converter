package com.bereda.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@Slf4j
@ControllerAdvice
public class ExceptionHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @org.springframework.web.bind.annotation.ExceptionHandler(CurrencyExchangeRateDoesNotExistException.class)
    public ResponseEntity<ErrorDetails> handleCurrencyExchangeRateDoesNotExistException(CurrencyExchangeRateDoesNotExistException exception, WebRequest request) {
        log.error("An exception occurred", exception);
        return buildResponseEntity(ErrorDetails.builder()
                .message(exception.getMessage())
                .details(request.getDescription(false))
                .status(HttpStatus.NOT_FOUND)
                .build());
    }

    private ResponseEntity<ErrorDetails> buildResponseEntity(ErrorDetails errorDetails) {
        return new ResponseEntity<>(errorDetails, errorDetails.getStatus());
    }

}

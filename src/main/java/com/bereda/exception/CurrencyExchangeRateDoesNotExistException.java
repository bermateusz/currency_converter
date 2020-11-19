package com.bereda.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Exchange rate does not exist")
public class CurrencyExchangeRateDoesNotExistException extends RuntimeException {
    public CurrencyExchangeRateDoesNotExistException(String message) {
        super(message);
    }
}

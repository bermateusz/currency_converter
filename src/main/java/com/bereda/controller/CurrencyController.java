package com.bereda.controller;

import com.bereda.model.ExchangeRateDTO;
import com.bereda.service.CurrencyService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping(value = "api/currencies")
public class CurrencyController {

    private final CurrencyService currencyService;

    public CurrencyController(final CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping
    public ExchangeRateDTO findExchangeRate(@RequestParam final String from, @RequestParam final String to,
                                            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate createdAt) {
        return new ExchangeRateDTO(currencyService.findExchangeRate(from, to, createdAt));
    }


}



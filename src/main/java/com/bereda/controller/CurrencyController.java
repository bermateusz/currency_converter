package com.bereda.controller;

import com.bereda.model.ExchangeRateDTO;
import com.bereda.service.CurrencyService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/currencies")
public class CurrencyController {

    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @RequestMapping(
            params = {"from", "to"},
            method = RequestMethod.GET)
    public ExchangeRateDTO exchangeRate(@RequestParam String from, @RequestParam String to) {
        return new ExchangeRateDTO(currencyService.findExchangeRate(from, to));
    }


}



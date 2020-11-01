package com.bereda.controller;

import com.bereda.model.ExchangeRateDTO;
import com.bereda.entity.Currency;
import com.bereda.entity.CurrencyDTO;
import com.bereda.service.CurrencyService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/currencies")
public class CurrencyController {

    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Currency saveCurrency(@RequestBody CurrencyDTO currencyDTO) {
        return currencyService.save(currencyDTO);
    }


    @RequestMapping(
            params = { "from", "to" },
            method = RequestMethod.GET)
    public ExchangeRateDTO exchangeRate(@RequestParam String from, @RequestParam String to){
    return new ExchangeRateDTO(currencyService.findExchangeRate(from,to));
    }


}



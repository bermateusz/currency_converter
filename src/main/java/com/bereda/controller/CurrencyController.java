package com.bereda.controller;

import com.bereda.model.ExchangeRateDTO;
import com.bereda.service.CurrencyService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping(value = "api/currencies")
@AllArgsConstructor
public class CurrencyController {

    private final CurrencyService currencyService;

    @GetMapping
    public ExchangeRateDTO findExchangeRate(@RequestParam final String from, @RequestParam final String to,
                                            @RequestParam(required = false)
                                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate createdAt) {
        return ExchangeRateDTO.builder()
                .value(currencyService.findExchangeRate(from,to,createdAt))
                .build();
    }


}



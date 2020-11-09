package com.bereda.service;

import com.bereda.entity.Currency;
import com.bereda.exception.CurrencyExchangeRateDoesNotExistException;
import com.bereda.repository.CurrencyRepository;
import org.springframework.stereotype.Service;

import static java.time.LocalDateTime.now;

@Service
public class CurrencyService {

    private final CurrencyRepository currencyRepository;

    public CurrencyService(final CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    public void save(final String from, final String to, final Double value) {
        currencyRepository.save(new Currency(from, to, value, now()));
    }

    public Double findExchangeRate(final String from, final String to) {
        if (from.equals(to)) {
            return 1.0;
        }
        return currencyRepository.findFirstByFromAndToOrderByCreatedAtDesc(from, to)
                .map(Currency::getValue)
                .or(() -> currencyRepository.findFirstByFromAndToOrderByCreatedAtDesc(to, from)
                        .map(Currency::getValue))
                .orElseThrow(() -> new CurrencyExchangeRateDoesNotExistException("Currency exchange rate from "
                        + from + " to " + to + " does not exist"));
    }


}

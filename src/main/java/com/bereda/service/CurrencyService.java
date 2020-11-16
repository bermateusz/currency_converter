package com.bereda.service;

import com.bereda.entity.Currency;
import com.bereda.exception.CurrencyExchangeRateDoesNotExistException;
import com.bereda.repository.CurrencyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDate;

import static java.time.LocalDateTime.now;

@AllArgsConstructor
@Service
public class CurrencyService {

    private final CurrencyRepository currencyRepository;
    private final Clock clock;
//
//    public CurrencyService(final CurrencyRepository currencyRepository, final Clock clock) {
//        this.currencyRepository = currencyRepository;
//        this.clock = clock;
//    }

    public void save(final String from, final String to, final Double value) {
        final Currency entity = new Currency(from, to, value, now(clock));
        currencyRepository.save(entity);
    }

    public Double findExchangeRate(final String from, final String to, final LocalDate createdAt) {
        if (from.equals(to)) {
            return 1.0;
        }
        if (createdAt != null) {
            return currencyRepository.findExchangeRateByDate(from, to, createdAt)
                    .map(Currency::getValue)
                    .or(() -> currencyRepository.findExchangeRateByDate(to, from, createdAt)
                            .map(currency -> 1 / currency.getValue()))
                    .orElseThrow(() -> new CurrencyExchangeRateDoesNotExistException("Currency exchange rate from "
                            + from + " to " + to + " created at " + createdAt + " does not exist"));
        }
        return currencyRepository.findFirstByFromAndToOrderByCreatedAtDesc(from, to)
                .map(Currency::getValue)
                .or(() -> currencyRepository.findFirstByFromAndToOrderByCreatedAtDesc(to, from)
                        .map(currency -> 1 / currency.getValue()))
                .orElseThrow(() -> new CurrencyExchangeRateDoesNotExistException("Currency exchange rate from "
                        + from + " to " + to + " does not exist"));
    }
}

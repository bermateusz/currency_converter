package com.bereda.service;

import com.bereda.entity.Currency;
import com.bereda.entity.CurrencyDTO;
import com.bereda.exception.CurrencyExchangeRateDoesNotExistException;
import com.bereda.repository.CurrencyRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CurrencyService {
    private final CurrencyRepository currencyRepository;

    public CurrencyService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    public Currency save(CurrencyDTO currencyDTO) {
        final Currency currency = new Currency(currencyDTO);
        return currencyRepository.save(currency);
    }


    public Double findExchangeRate(String from, String to) {
        final Optional<Currency> search1 = currencyRepository.findFirstByFromAndToOrderByCreatedAtDesc(from, to);
        if (search1.isPresent()) {
            return search1.get().getValue();
        } else {
            final Optional<Currency> search2 = currencyRepository.findFirstByFromAndToOrderByCreatedAtDesc(to, from);
            if (search2.isPresent()) {
                return 1 / search2.get().getValue();
            }
        }
        throw new CurrencyExchangeRateDoesNotExistException("Currency exchange rate from " + from + " to " + to + " does not exist");
    }


// zwracac 404 zamiast runtime exception rzucic normalny wyjatek msg finale dodac uporzadkowany kot dopisac test do findexchangerate
//    zamienic optionale
}

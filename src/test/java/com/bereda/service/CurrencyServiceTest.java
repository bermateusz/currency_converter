package com.bereda.service;

import com.bereda.entity.Currency;
import com.bereda.repository.CurrencyRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.when;

class CurrencyServiceTest {

    @InjectMocks
    private CurrencyService currencyService;

    @Mock
    private CurrencyRepository currencyRepository;

    private final Fixtures fixtures = new Fixtures();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void shouldFindExchangeRateValueFromEurToPln() {
        //given
        Currency currency = fixtures.currency;
        String from = fixtures.from;
        String to = fixtures.to;
        when(currencyRepository.findFirstByFromAndToOrderByCreatedAtDesc(from, to)).thenReturn(Optional.of(currency));
//        when(currencyRepository.findFirstByFromAndToOrderByCreatedAtDesc(to, from)).thenReturn(Optional.empty());
        //when
        Double exchangeRateValue = currencyService.findExchangeRate(from,to);
        //then
        Assertions.assertEquals(4.60,exchangeRateValue);
    }

    @Test
    void shouldFindExchangeRateValueFromPlnToEur() {
        //given
        Currency currency = fixtures.currency;
        String from = fixtures.from;
        String to = fixtures.to;
        when(currencyRepository.findFirstByFromAndToOrderByCreatedAtDesc(to, from)).thenReturn(Optional.of(currency));
        //when
        Double exchangeRateValue  = currencyService.findExchangeRate(from,to);;
        //then
        Assertions.assertEquals(fixtures.PlnToEuro,exchangeRateValue);
    }



    public static class Fixtures {

        Currency currency = createFakeCurrency();
        String from = "EUR";
        String to = "PLN";
        Double PlnToEuro = 1/4.60;

        private Currency createFakeCurrency() {
            Currency currency = new Currency();
            currency.setId(1L);
            currency.setFrom("EUR");
            currency.setTo("PLN");
            currency.setValue(4.60);
            currency.setCreatedAt(LocalDateTime.now());
            return currency;
        }
    }
}
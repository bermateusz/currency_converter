package com.bereda.service;

import com.bereda.entity.Currency;
import com.bereda.repository.CurrencyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CurrencyRepositoryTest {

    private static final Fixtures fixtures = new Fixtures();

    @Mock
    private CurrencyRepository currencyRepository;

    @InjectMocks
    private com.bereda.service.CurrencyService currencyService;

    @Test
    void shouldFindExchangeRateValueFromEurToPln() {
        //given
        when(currencyRepository.findFirstByFromAndToOrderByCreatedAtDesc(fixtures.from, fixtures.to)).thenReturn(Optional.of(fixtures.eurToPlnData));

        //when
//        Double exchangeRateValue = currencyRepository.findExchangeRate(fixtures.from, fixtures.to);

        //then
//        assertEquals(fixtures.rateEurToPln, exchangeRateValue);
    }

    @Test
    void shouldFindExchangeRateValueFromEurToPlnByDivingByInverse() {
        //given
        when(currencyRepository.findFirstByFromAndToOrderByCreatedAtDesc(fixtures.from, fixtures.to)).thenReturn(Optional.empty());
        when(currencyRepository.findFirstByFromAndToOrderByCreatedAtDesc(fixtures.to, fixtures.from)).thenReturn(Optional.of(fixtures.plnToEurData));

        //when
//        Double exchangeRateValue = currencyRepository.findExchangeRate(fixtures.from, fixtures.to);


        //then
//        assertEquals(fixtures.ratePlnToEur, exchangeRateValue);
    }

    @Test
    void shouldThrowExceptionWhenDoNotFindExchangeRateValueFromEurToPln() {
        //given
        when(currencyRepository.findFirstByFromAndToOrderByCreatedAtDesc(fixtures.to, fixtures.to)).thenReturn(Optional.empty());
        when(currencyRepository.findFirstByFromAndToOrderByCreatedAtDesc(fixtures.to, fixtures.to)).thenReturn(Optional.empty());

        //when
//        assertThrows(CurrencyExchangeRateDoesNotExistException.class, () -> currencyRepository.findExchangeRate(fixtures.to, fixtures.to));

        //then
    }


    public static class Fixtures {

        Currency eurToPlnData = createFakeEurToPlnData();
        Currency plnToEurData = createFakePlnToEurData();
        String from = "EUR";
        String to = "PLN";
        Double rateEurToPln = 4.54;
        Double ratePlnToEur = 0.22;


        private Currency createFakeEurToPlnData() {
            Currency currency = new Currency();
            currency.setId(1L);
            currency.setFrom("EUR");
            currency.setTo("PLN");
            currency.setValue(4.54);
            currency.setCreatedAt(LocalDateTime.now());
            return currency;
        }

        private Currency createFakePlnToEurData() {
            Currency currency = new Currency();
            currency.setId(1L);
            currency.setFrom("PLN");
            currency.setTo("EUR");
            currency.setValue(0.22);
            currency.setCreatedAt(LocalDateTime.now());
            return currency;
        }
    }
}
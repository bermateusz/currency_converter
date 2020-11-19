package com.bereda.service;

import com.bereda.entity.Currency;
import com.bereda.exception.CurrencyExchangeRateDoesNotExistException;
import com.bereda.repository.CurrencyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CurrencyServiceTest {

    private static final Fixtures fixtures = new Fixtures();

    @Mock
    private CurrencyRepository currencyRepository;

    @InjectMocks
    private CurrencyService currencyService;

    @Test
    void shouldSaveCurrency() {
        //given
        final LocalDateTime fakeNow = LocalDateTime.of(2020, 12, 12, 5, 55);
        final Clock fakeClock = Clock.fixed(fakeNow.atZone(ZoneId.of("GMT+1")).toInstant(), ZoneId.of("GMT+1"));
        final CurrencyService currencyServiceWithFixedClock = new CurrencyService(currencyRepository, fakeClock);

        //when
        currencyServiceWithFixedClock.save(fixtures.from, fixtures.to, 9.099);

        //then
        final ArgumentCaptor<Currency> argumentCaptor = ArgumentCaptor.forClass(Currency.class);
        verify(currencyRepository).save(argumentCaptor.capture());
        final Currency currency = argumentCaptor.getValue();
        assertThat(currency.getFrom()).isEqualTo(fixtures.from);
        assertThat(currency.getTo()).isEqualTo(fixtures.to);
        assertThat(currency.getValue()).isEqualTo(9.099);
        assertThat(currency.getCreatedAt()).isEqualTo(fakeNow);
    }

    @Test
    void shouldReturnOneWhenFromIsEqualToTo() {
        //given
        String from = fixtures.from;

        //when
        Double exchangeRate = currencyService.findExchangeRate(from, from, null);

        //then
        assertThat(exchangeRate).isEqualTo(1.0);
    }

    @Test
    void shouldReturnExchangeRateValueCreatedAtWhenEurToPlnRateExist() {
        //given
        when(currencyRepository.findExchangeRateByDate(fixtures.from, fixtures.to, fixtures.localDate)).thenReturn(Optional.of(fixtures.eurToPlnData));

        //when
        Double exchangeRate = currencyService.findExchangeRate(fixtures.from, fixtures.to, fixtures.localDate);

        //then
        assertThat(exchangeRate).isEqualTo(fixtures.rateEurToPln);
    }

    @Test
    void shouldReturnExchangeRateValueCreatedAtWhenEurToPlnDoesNotExist() {
        //given
        when(currencyRepository.findExchangeRateByDate(fixtures.from, fixtures.to, fixtures.localDate)).thenReturn(Optional.empty());
        when(currencyRepository.findExchangeRateByDate(fixtures.to, fixtures.from, fixtures.localDate)).thenReturn(Optional.of(fixtures.plnToEurData));

        //when
        Double exchangeRate = currencyService.findExchangeRate(fixtures.from, fixtures.to, fixtures.localDate);

        //then
        assertThat(exchangeRate).isEqualTo(fixtures.rateEurToPln);
    }

    @Test
    void shouldThrowExceptionWhenDoNotFindExchangeRateValueCreatedArFromEurToPln() {
        //given
        when(currencyRepository.findExchangeRateByDate(fixtures.from, fixtures.to, fixtures.localDate)).thenReturn(Optional.empty());
        when(currencyRepository.findExchangeRateByDate(fixtures.from, fixtures.to, fixtures.localDate)).thenReturn(Optional.empty());

        //when
        final Throwable throwable = catchThrowable(() -> currencyService.findExchangeRate(fixtures.from, fixtures.to, fixtures.localDate));

        //then
        assertThat(throwable)
                .isInstanceOf(CurrencyExchangeRateDoesNotExistException.class);
    }


    @Test
    void shouldFindExchangeRateValueFromEurToPln() {
        //given
        when(currencyRepository.findFirstByFromAndToOrderByCreatedAtDesc(fixtures.from, fixtures.to)).thenReturn(Optional.of(fixtures.eurToPlnData));

        //when
        final Double exchangeRateValue = currencyService.findExchangeRate(fixtures.from, fixtures.to, null);

        //then
        assertThat(exchangeRateValue).isEqualTo(fixtures.rateEurToPln);
    }

    @Test
    void shouldFindExchangeRateValueFromEurToPlnByDivingByInverse() {
        //given
        when(currencyRepository.findFirstByFromAndToOrderByCreatedAtDesc(fixtures.from, fixtures.to)).thenReturn(Optional.empty());
        when(currencyRepository.findFirstByFromAndToOrderByCreatedAtDesc(fixtures.to, fixtures.from)).thenReturn(Optional.of(fixtures.plnToEurData));

        //when
        final Double exchangeRateValue = currencyService.findExchangeRate(fixtures.from, fixtures.to, null);

        //then
        assertThat(exchangeRateValue).isEqualTo(fixtures.rateEurToPln);
    }

    @Test
    void shouldThrowExceptionWhenDoNotFindExchangeRateValueFromEurToPln() {
        //given
        when(currencyRepository.findFirstByFromAndToOrderByCreatedAtDesc(fixtures.from, fixtures.to)).thenReturn(Optional.empty());
        when(currencyRepository.findFirstByFromAndToOrderByCreatedAtDesc(fixtures.to, fixtures.from)).thenReturn(Optional.empty());

        //when
        final Throwable throwable = catchThrowable(() -> currencyService.findExchangeRate(fixtures.from, fixtures.to, null));

        //then
        assertThat(throwable)
                .isInstanceOf(CurrencyExchangeRateDoesNotExistException.class);
    }


    public static class Fixtures {
        Currency eurToPlnData = createFakeEurToPlnData();
        Currency plnToEurData = createFakePlnToEurData();
        String from = "EUR";
        String to = "PLN";
        Double rateEurToPln = 4.00;
        LocalDate localDate = LocalDate.of(2020, 12, 12);
        LocalDateTime localDateTime = LocalDateTime.of(2020,12,12,5,55);

        private Currency createFakeEurToPlnData() {
            return Currency.builder()
                    .id(1L)
                    .from("EUR")
                    .to("PLN")
                    .value(4.00)
                    .createdAt(localDateTime)
                    .build();
        }

        private Currency createFakePlnToEurData() {
            return Currency.builder()
                    .id(1L)
                    .from("PLN")
                    .to("EUR")
                    .value(1/4.00)
                    .createdAt(localDateTime)
                    .build();
        }
    }
}
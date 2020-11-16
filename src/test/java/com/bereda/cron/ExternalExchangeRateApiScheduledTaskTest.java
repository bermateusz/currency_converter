package com.bereda.cron;

import com.bereda.external_api.model.ExternalExchangeRateResponse;
import com.bereda.external_api.service.ExternalExchangeRateApiService;
import com.bereda.service.CurrencyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExternalExchangeRateApiScheduledTaskTest {

    private static final Fixtures fixtures = new Fixtures();

    @Mock
    private ExternalExchangeRateApiService externalExchangeRateApiService;

    @Mock
    private CurrencyService currencyService;

    private ExternalExchangeRateApiScheduledTask externalExchangeRateApiScheduledTask;

    @Test
    void shouldReturnOptionalEmpty(){
        //given
        externalExchangeRateApiScheduledTask = new ExternalExchangeRateApiScheduledTask(externalExchangeRateApiService,currencyService,List.of("PLN","USD"));
        when(externalExchangeRateApiService.exchangeRateApiRequest("PLN")).thenReturn(Optional.empty());
        when(externalExchangeRateApiService.exchangeRateApiRequest("USD")).thenReturn(Optional.of(fixtures.fakeExchangeRateResponseForUsd));


        //when
        externalExchangeRateApiScheduledTask.returnExchangeRateAndSave();

        //then
        verify(currencyService).save("USD", "AED", 3.673);
        verify(currencyService).save("USD", "AFN", 77.00);
        verify(currencyService).save("USD", "ALL", 104.807885);
        verify(currencyService).save("USD", "AMD", 481.616225);
        verify(currencyService).save("USD", "ANG", 1.791162);
        verify(currencyService).save("USD", "AOA", 665.784995);
        verifyNoMoreInteractions(currencyService);

    }
    @Test
    void shouldSaveExchangeRateForOneCurrencyFromList() {
        //given
        externalExchangeRateApiScheduledTask = new ExternalExchangeRateApiScheduledTask(externalExchangeRateApiService, currencyService, List.of("PLN"));
        when(externalExchangeRateApiService.exchangeRateApiRequest("PLN")).thenReturn(Optional.of(fixtures.fakeExchangeRateResponseForPln));

        //when
        externalExchangeRateApiScheduledTask.returnExchangeRateAndSave();

        //then
        verify(currencyService).save("PLN", "AED", 0.970862);
        verify(currencyService).save("PLN", "AFN", 20.306704);
        verify(currencyService).save("PLN", "ALL", 27.54813);
        verify(currencyService).save("PLN", "AMD", 127.295754);
        verify(currencyService).save("PLN", "ANG", 0.473994);
        verify(currencyService).save("PLN", "AOA", 175.794905);
        verifyNoMoreInteractions(currencyService);
    }

    @Test
    void shouldSaveExchangeRateForTwoCurrenciesFromList() {
        //given
        externalExchangeRateApiScheduledTask = new ExternalExchangeRateApiScheduledTask(externalExchangeRateApiService, currencyService, List.of("PLN", "USD"));
        when(externalExchangeRateApiService.exchangeRateApiRequest("PLN")).thenReturn(Optional.of(fixtures.fakeExchangeRateResponseForPln));
        when(externalExchangeRateApiService.exchangeRateApiRequest("USD")).thenReturn(Optional.of(fixtures.fakeExchangeRateResponseForUsd));

        //when
        externalExchangeRateApiScheduledTask.returnExchangeRateAndSave();

        //then
        verify(currencyService).save("PLN", "AED", 0.970862);
        verify(currencyService).save("PLN", "AFN", 20.306704);
        verify(currencyService).save("PLN", "ALL", 27.54813);
        verify(currencyService).save("PLN", "AMD", 127.295754);
        verify(currencyService).save("PLN", "ANG", 0.473994);
        verify(currencyService).save("PLN", "AOA", 175.794905);

        verify(currencyService).save("USD", "AED", 3.673);
        verify(currencyService).save("USD", "AFN", 77.00);
        verify(currencyService).save("USD", "ALL", 104.807885);
        verify(currencyService).save("USD", "AMD", 481.616225);
        verify(currencyService).save("USD", "ANG", 1.791162);
        verify(currencyService).save("USD", "AOA", 665.784995);
        verifyNoMoreInteractions(currencyService);
    }

    private static class Fixtures {
        final ExternalExchangeRateResponse fakeExchangeRateResponseForPln = fakeExchangeRateResponseForPln();
        final ExternalExchangeRateResponse fakeExchangeRateResponseForUsd = fakeExchangeRateResponseForUsd();
        List<String> list = List.of("PLN","USD");

        private ExternalExchangeRateResponse fakeExchangeRateResponseForPln() {
            ExternalExchangeRateResponse fakeExternalRateResponseForPln = new ExternalExchangeRateResponse();
            Map<String, Double> plnCurrencyMap = new HashMap<>();

            plnCurrencyMap.put("AED", 0.970862);
            plnCurrencyMap.put("AFN", 20.306704);
            plnCurrencyMap.put("ALL", 27.54813);
            plnCurrencyMap.put("AMD", 127.295754);
            plnCurrencyMap.put("ANG", 0.473994);
            plnCurrencyMap.put("AOA", 175.794905);

            fakeExternalRateResponseForPln.setBase("PLN");
            fakeExternalRateResponseForPln.setRates(plnCurrencyMap);
            return fakeExternalRateResponseForPln;
        }

        private ExternalExchangeRateResponse fakeExchangeRateResponseForUsd() {
            ExternalExchangeRateResponse fakeExternalRateResponseForUsd = new ExternalExchangeRateResponse();
            Map<String, Double> usdCurrencyMap = new HashMap<>();

            usdCurrencyMap.put("AED", 3.673);
            usdCurrencyMap.put("AFN", 77.00);
            usdCurrencyMap.put("ALL", 104.807885);
            usdCurrencyMap.put("AMD", 481.616225);
            usdCurrencyMap.put("ANG", 1.791162);
            usdCurrencyMap.put("AOA", 665.784995);

            fakeExternalRateResponseForUsd.setBase("USD");
            fakeExternalRateResponseForUsd.setRates(usdCurrencyMap);
            return fakeExternalRateResponseForUsd;
        }

    }
}
package com.bereda.cron;

import com.bereda.external_api.model.ExternalExchangeRateResponse;
import com.bereda.external_api.service.ExternalExchangeRateApiService;
import com.bereda.service.CurrencyService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ExternalExchangeRateApiScheduledTask {

    private final ExternalExchangeRateApiService externalExchangeRateApiService;
    private final CurrencyService currencyService;


    public ExternalExchangeRateApiScheduledTask(final ExternalExchangeRateApiService externalExchangeRateApiService,
                                                final CurrencyService currencyService) {
        this.externalExchangeRateApiService = externalExchangeRateApiService;
        this.currencyService = currencyService;
    }

    @Scheduled(cron = "*/15 * * * * *")
    public void returnExchangeRateAndSave() {
        final ExternalExchangeRateResponse externalExchangeRateResponse = externalExchangeRateApiService.exchangeRateApiRequest();

        for (Map.Entry<String, Double> entry : externalExchangeRateResponse.getRates().entrySet()) {
            currencyService.save("PLN", entry.getKey(), entry.getValue());
        }

    }
}
//masz napisac test do scheduledtaska ponadto url do api jako propertke w aplication.properties w xchange mam przekazywac walute a nei na sztywno
//pln w app properties robisz liste walut ktora supportuje i ktora importuje np pare walut ktore mam zaimportowac (pln eur gbt usd)
//dorob logike do wyciagania do xchange rate ze jak pln do pln to jeden musi zwracac z tych samych walut wartosc 1 bo po co
// szukac properties na yml/ayml 

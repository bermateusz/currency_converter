package com.bereda.cron;

import com.bereda.external_api.model.ExternalExchangeRateResponse;
import com.bereda.external_api.service.ExternalExchangeRateApiService;
import com.bereda.service.CurrencyService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class ExternalExchangeRateApiScheduledTask {

    private final ExternalExchangeRateApiService externalExchangeRateApiService;
    private final CurrencyService currencyService;
    private final List<String> currenciesToImport;


    public ExternalExchangeRateApiScheduledTask(final ExternalExchangeRateApiService externalExchangeRateApiService,
                                                final CurrencyService currencyService, @Value("${currencies}") List<String> currenciesToImport) {
        this.externalExchangeRateApiService = externalExchangeRateApiService;
        this.currencyService = currencyService;
        this.currenciesToImport = currenciesToImport;
    }

    @Scheduled(cron = "* */5 * * * *")
    public void returnExchangeRateAndSave() {
        currenciesToImport.forEach(currency -> {
            externalExchangeRateApiService.exchangeRateApiRequest(currency)
                    .map(ExternalExchangeRateResponse::getRates)
                    .ifPresent(rates -> {
                        for (Map.Entry<String, Double> entry : rates.entrySet()) {
                            currencyService.save(currency, entry.getKey(), entry.getValue());
                        }
                    });
        });
    }
}

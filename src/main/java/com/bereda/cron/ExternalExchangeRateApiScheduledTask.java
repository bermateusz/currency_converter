package com.bereda.cron;

import com.bereda.external_api.model.ExternalExchangeRateResponse;
import com.bereda.external_api.service.ExternalExchangeRateApiService;
import com.bereda.service.CurrencyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class ExternalExchangeRateApiScheduledTask {

    private final ExternalExchangeRateApiService externalExchangeRateApiService;
    private final CurrencyService currencyService;
    private final List<String> currenciesToImport;

    public ExternalExchangeRateApiScheduledTask(final ExternalExchangeRateApiService externalExchangeRateApiService, final CurrencyService currencyService,
                                                @Value("${currencies}") final List<String> currenciesToImport) {
        this.externalExchangeRateApiService = externalExchangeRateApiService;
        this.currencyService = currencyService;
        this.currenciesToImport = currenciesToImport;
    }

    @Scheduled(cron = "${cron.expression}")
    public void returnExchangeRateAndSave() {
        log.info("Starting to import currencies: {}", currenciesToImport);
        currenciesToImport.forEach(currency -> {
            externalExchangeRateApiService.exchangeRateApiRequest(currency)
                    .map(ExternalExchangeRateResponse::getRates)
                    .ifPresent(rates -> {
                        log.info("Saving currency: {} to database", currency);
                        for (Map.Entry<String, Double> entry : rates.entrySet()) {
                            currencyService.save(currency, entry.getKey(), entry.getValue());
                        }
                    });
        });
        log.info("Finishing currencies import");
    }
}

package com.bereda.externalApi.cron;

import com.bereda.entity.Currency;
import com.bereda.externalApi.model.ExternalExchangeRateResponse;
import com.bereda.repository.CurrencyRepository;
import com.bereda.externalApi.service.ExternalExchangeRateApiService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;

import static java.time.LocalDateTime.now;

@Component
public class ExternalExchangeRateApiScheduledTask {

    private final ExternalExchangeRateApiService externalExchangeRateApiService;
    private final CurrencyRepository currencyRepository;


    public ExternalExchangeRateApiScheduledTask(final ExternalExchangeRateApiService externalExchangeRateApiService,
                                                final CurrencyRepository currencyRepository) {
        this.externalExchangeRateApiService = externalExchangeRateApiService;
        this.currencyRepository = currencyRepository;
    }

    @Scheduled(cron = "*/15 * * * * *")
    public void returnExchangeRateAndSave() {
        final ExternalExchangeRateResponse externalExchangeRateResponse = externalExchangeRateApiService.exchangeRateApiRequest();

        for (Map.Entry<String, Double> entry : externalExchangeRateResponse.getRates().entrySet()) {
            currencyRepository.save(new Currency(null, "PLN", entry.getKey(), entry.getValue(), now()));
        }

    }
}

package com.bereda.external_api.service;

import com.bereda.external_api.model.ExternalExchangeRateResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Slf4j
@Service
public class ExternalExchangeRateApiService {

    private final RestTemplate restTemplate;
    private final String baseUrl;

    public ExternalExchangeRateApiService(final RestTemplate restTemplate, @Value("${apiRequest.url}") final String baseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
    }

    public Optional<ExternalExchangeRateResponse> exchangeRateApiRequest(final String currency) {
        try {
            final ResponseEntity<ExternalExchangeRateResponse> currencyDTOResponseEntity =
                    restTemplate.getForEntity(baseUrl + currency, ExternalExchangeRateResponse.class);
            return Optional.ofNullable(currencyDTOResponseEntity.getBody());
        } catch (final Exception e) {
            log.error("An exception occurred when importing currency: {}", currency, e);
            return Optional.empty();
        }
    }
}

package com.bereda.externalApi.service;

import com.bereda.externalApi.model.ExternalExchangeRateResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExternalExchangeRateApiService {

    private final RestTemplate restTemplate;

    private final String baseUrl;

    public ExternalExchangeRateApiService(final RestTemplate restTemplate,    @Value("${apiRequest.url}") final String baseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public ExternalExchangeRateResponse exchangeRateApiRequest() {
        final ResponseEntity<ExternalExchangeRateResponse> currencyDTOResponseEntity = restTemplate.getForEntity(baseUrl, ExternalExchangeRateResponse.class);
        return currencyDTOResponseEntity.getBody();

    }
}

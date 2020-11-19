package com.bereda.external_api.service;

import com.bereda.external_api.model.ExternalExchangeRateResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ExternalExchangeRateApiService {

    private final RestTemplate restTemplate;
    private final String baseUrl;

    public Optional<ExternalExchangeRateResponse> exchangeRateApiRequest(final String currency) {
        try{
            final ResponseEntity<ExternalExchangeRateResponse> currencyDTOResponseEntity =
                    restTemplate.getForEntity(baseUrl + currency, ExternalExchangeRateResponse.class);
            return Optional.ofNullable(currencyDTOResponseEntity.getBody());
        }catch (final Exception e){
            return Optional.empty();
        }
    }
}

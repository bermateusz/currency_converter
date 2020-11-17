package com.bereda.external_api.model;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Builder
@Data
public class ExternalExchangeRateResponse {
    private String base;
    private Map<String, Double> rates;
}


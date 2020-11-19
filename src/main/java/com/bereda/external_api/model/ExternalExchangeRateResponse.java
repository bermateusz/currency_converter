package com.bereda.external_api.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

import java.util.Map;

@Builder
@Value
@JsonDeserialize(builder = ExternalExchangeRateResponse.ExternalExchangeRateResponseBuilder.class)
public class ExternalExchangeRateResponse {
    String base;
    Map<String, Double> rates;

    @JsonPOJOBuilder(withPrefix = "")
    public static final class ExternalExchangeRateResponseBuilder {}
}


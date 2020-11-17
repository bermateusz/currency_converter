package com.bereda.external_api.model;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Builder
@Data
public class ExternalExchangeRateResponse {
    private String base;
    private Map<String, Double> rates;


    public ExternalExchangeRateResponse() {
    }

    public ExternalExchangeRateResponse(String base, Map<String, Double> rates) {
        this.base = base;
        this.rates = rates;
    }

//    public String getBase() {
//        return base;
//    }
//
//    public void setBase(String base) {
//        this.base = base;
//    }
//
//    public Map<String, Double> getRates() {
//        return rates;
//    }
//
//    public void setRates(Map<String, Double> rates) {
//        this.rates = rates;
//    }

}


package com.bereda.entity;

public class CurrencyDTO {
    private String from;
    private String to;
    private Double value;

    public CurrencyDTO() {
    }

    public CurrencyDTO(String from, String to, Double value) {
        this.from = from;
        this.to = to;
        this.value = value;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}

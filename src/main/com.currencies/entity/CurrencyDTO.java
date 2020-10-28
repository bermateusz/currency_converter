package entity;

import java.time.LocalDateTime;

public class CurrencyDTO {
    private String from;
    private String to;
    private Double value;
    private LocalDateTime datestamp;

    public CurrencyDTO() {
    }

    public CurrencyDTO(String from, String to, Double value, LocalDateTime datestamp) {
        this.from = from;
        this.to = to;
        this.value = value;
        this.datestamp = datestamp;
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

    public LocalDateTime getDatestamp() {
        return datestamp;
    }

    public void setDatestamp(LocalDateTime datestamp) {
        this.datestamp = datestamp;
    }
}

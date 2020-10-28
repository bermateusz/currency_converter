package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "currencies")
public class Currency {

    @Id
    @GeneratedValue
    private Long id;
    private String from;
    private String to;
    private Double value;
    private LocalDateTime datestamp;

    public Currency() {
    }

    public Currency(Long id, String from, String to, Double value, LocalDateTime datestamp) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.value = value;
        this.datestamp = datestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

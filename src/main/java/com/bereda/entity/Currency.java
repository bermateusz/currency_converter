package com.bereda.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@Entity
@Table(name = "currencies")
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "from_currency")
    private String from;
    @Column(name = "to_currency")
    private String to;
    private Double value;
    private LocalDateTime createdAt;

    public Currency() {
    }

    public Currency(String from, String to, Double value, LocalDateTime createdAt) {
        this.from = from;
        this.to = to;
        this.value = value;
        this.createdAt = createdAt;
    }
}

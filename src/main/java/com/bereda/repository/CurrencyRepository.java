package com.bereda.repository;

import com.bereda.entity.Currency;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface CurrencyRepository extends CrudRepository<Currency, Long> {

    Optional<Currency> findFirstByFromAndToOrderByCreatedAtDesc(String from, String to);

    @Query(value = "SELECT * FROM currencies value WHERE from_currency =?1 AND to_currency =?2 AND DATE(created_at) =?3 ORDER BY created_at DESC LIMIT 1", nativeQuery = true)
    Optional<Currency> findExchangeRateByDate(String from, String to, LocalDate date);


}


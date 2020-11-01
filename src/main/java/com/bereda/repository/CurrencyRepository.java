package com.bereda.repository;

import com.bereda.entity.Currency;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CurrencyRepository extends CrudRepository<Currency, Long> {

    Optional<Currency> findFirstByFromAndToOrderByCreatedAtDesc(String from, String to);

}

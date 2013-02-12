package com.expense.repository;

import org.springframework.data.repository.CrudRepository;

import com.expense.domain.CurrencyConversion;

public interface CurrencyConversionRepository extends
    CrudRepository<CurrencyConversion, Long> {

}

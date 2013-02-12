package com.expense.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.history.RevisionRepository;

import com.expense.domain.CurrencyConversion;

public interface CurrencyConversionRepository extends
    CrudRepository<CurrencyConversion, Long>,
    RevisionRepository<CurrencyConversion, Long, Integer> {

}

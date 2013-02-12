package com.expense.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.history.RevisionRepository;

import com.expense.domain.Expense;

public interface ExpenseRepository extends CrudRepository<Expense, Long>,
    RevisionRepository<Expense, Long, Integer> {

}

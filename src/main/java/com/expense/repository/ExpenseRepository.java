package com.expense.repository;

import org.springframework.data.repository.CrudRepository;

import com.expense.domain.Expense;

public interface ExpenseRepository extends CrudRepository<Expense, Long> {

}

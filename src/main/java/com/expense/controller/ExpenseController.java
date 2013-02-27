package com.expense.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.expense.domain.Expense;
import com.expense.repository.ExpenseRepository;

@Controller
@RequestMapping("/expense")
public class ExpenseController {

  private final ExpenseRepository expenseRepository;

  @Autowired
  public ExpenseController(ExpenseRepository expenseRepository) {
    this.expenseRepository = expenseRepository;
  }

  @RequestMapping(method = RequestMethod.GET)
  @ResponseBody
  public Iterable<Expense> allExpenses() {
    return expenseRepository.findAll();
  }

  @RequestMapping(value = "/new", method = RequestMethod.GET)
  @ResponseBody
  public Expense getNewExpense() {
    return new Expense();
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  @ResponseBody
  public Expense getExpense(@PathVariable("id") Long id) {
    return expenseRepository.findOne(id);
  }

  @RequestMapping(method = RequestMethod.POST)
  @ResponseBody
  public Long createExpense(@RequestBody Expense expense) {
    Expense saved = expenseRepository.save(expense);
    return saved.getId();
  }

  @RequestMapping(method = RequestMethod.PUT)
  @ResponseBody
  public void updateExpense(@RequestBody Expense expense) {
    expenseRepository.save(expense);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  @ResponseBody
  public void deleteCategory(@PathVariable("id") Long id) {
    Expense expense = expenseRepository.findOne(id);
    expenseRepository.delete(expense);
  }

}

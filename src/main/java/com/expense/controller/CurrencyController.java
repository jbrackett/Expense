package com.expense.controller;

import java.util.Collection;
import java.util.Currency;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.expense.service.CurrencyService;

@Controller
@RequestMapping("/currency")
public class CurrencyController {

  private Collection<Currency> currencies;

  public CurrencyController() {
    this.currencies = CurrencyService.getCurrencies();
  }

  @RequestMapping(method = RequestMethod.GET)
  @ResponseBody
  public Iterable<Currency> allCategories() {
    return currencies;
  }

}

package com.expense.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Currency;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.springframework.stereotype.Service;

@Service
public class CurrencyService {

  public CurrencyService() {

  }

  public static Collection<Currency> getCurrencies() {
    List<Currency> supportedCurrencies = new ArrayList<>();
    Set<Currency> currencies = Currency.getAvailableCurrencies();
    for (Currency currency : currencies) {
      String displayName = currency.getDisplayName(Locale.US);
      if (!displayName.contains("(") && displayName.length() > 3
          && !currency.getCurrencyCode().startsWith("X")) {
        supportedCurrencies.add(currency);
      }
    }
    Collections.sort(supportedCurrencies, new Comparator<Currency>() {
      @Override
      public int compare(Currency o1, Currency o2) {
        return o1.getCurrencyCode().compareTo(o2.getCurrencyCode());
      }
    });
    return supportedCurrencies;
  }

}

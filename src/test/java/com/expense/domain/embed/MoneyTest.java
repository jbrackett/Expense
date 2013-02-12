package com.expense.domain.embed;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;

import org.joda.time.LocalDate;
import org.junit.Test;

import com.expense.domain.CurrencyConversion;

public class MoneyTest {

  @Test
  public void moneyWithLocaleGivesExpectedResult() {
    Money dollar = new Money(Locale.US);
    assertEquals("USD", dollar.getCurrency().getCurrencyCode());

    Money euro = new Money(Locale.ITALY);
    assertEquals("EUR", euro.getCurrency().getCurrencyCode());
  }

  @Test(expected = IllegalArgumentException.class)
  public void exceptionWithBadCurrencyConversion() {
    Money dollar = new Money(Locale.US);

    CurrencyConversion conversion = new CurrencyConversion(
        Currency.getInstance("EUR"), Currency.getInstance("EUR"),
        BigDecimal.ONE, LocalDate.now());
    dollar.convert(conversion);
  }

  @Test
  public void currencyConversionWorks() {
    Money dollar = new Money(BigDecimal.TEN, Currency.getInstance("USD"));

    CurrencyConversion conversion = new CurrencyConversion(
        Currency.getInstance("USD"), Currency.getInstance("EUR"),
        new BigDecimal("2.5"), LocalDate.now());
    Money euro = dollar.convert(conversion);
    assertEquals(new BigDecimal("25.00"), euro.getAmount());
    assertEquals("EUR", euro.getCurrency().getCurrencyCode());
  }

}

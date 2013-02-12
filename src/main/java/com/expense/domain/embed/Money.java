package com.expense.domain.embed;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.Locale;

import javax.persistence.Embeddable;

import com.expense.domain.CurrencyConversion;

@Embeddable
public class Money implements Comparable<Money> {

  private BigDecimal amount;
  private Currency currency;

  public Money() {
    currency = Currency.getInstance("USD");
    amount = BigDecimal.ZERO.setScale(currency.getDefaultFractionDigits(),
        RoundingMode.HALF_UP);
  }

  public Money(Locale locale) {
    this();
    currency = Currency.getInstance(locale);
    amount.setScale(currency.getDefaultFractionDigits(), RoundingMode.HALF_UP);
  }

  public Money(BigDecimal amount, Currency currency) {
    this.currency = currency;
    this.amount = amount.setScale(currency.getDefaultFractionDigits(),
        RoundingMode.HALF_UP);
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public Currency getCurrency() {
    return currency;
  }

  public void setCurrency(Currency currency) {
    this.currency = currency;
  }

  /**
   * Determines if this currency is the same as the Money passed in.
   * 
   * @param money
   * @return true if currency is the same
   */
  public boolean isSameCurrency(Money money) {
    boolean result = false;
    if (currency != null) {
      result = currency.equals(money.getCurrency());
    }
    return result;
  }

  /**
   * Compares the amount of this Money to the given amount
   * 
   * @param amt
   *          amount to compare
   * @return a negative integer, zero, or a positive integer as this Money
   *         object is less than, equal to, or greater than the specified
   *         amount.
   */
  public int compareTo(BigDecimal amt) {
    return amount.compareTo(amt);
  }

  @Override
  public int compareTo(Money o) {
    if (isSameCurrency(o)) {
      return amount.compareTo(o.amount);
    }
    throw new IllegalArgumentException();
  }

  /**
   * Does a currency conversion given the CurrencyConversion
   * 
   * @param conversion
   *          the CurrencyConversion to use
   * @return a new Money object representing the finished conversion
   * 
   * @throws IllegalArgumentException
   *           if "from" field on conversion does not match this currency
   */
  public Money convert(CurrencyConversion conversion)
      throws IllegalArgumentException {
    Currency from = conversion.getOriginal();
    Currency to = conversion.getWanted();
    if (!from.equals(currency)) {
      throw new IllegalArgumentException(from.getCurrencyCode()
          + " does not match " + currency.getCurrencyCode());
    }
    BigDecimal newAmount = amount.multiply(conversion.getRate()).setScale(
        to.getDefaultFractionDigits());
    return new Money(newAmount, to);
  }

  @Override
  public String toString() {
    return "Money [amount=" + amount + ", currency=" + currency + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((amount == null) ? 0 : amount.hashCode());
    result = prime * result + ((currency == null) ? 0 : currency.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Money other = (Money) obj;
    if (amount == null) {
      if (other.amount != null)
        return false;
    }
    else if (!amount.equals(other.amount))
      return false;
    if (currency == null) {
      if (other.currency != null)
        return false;
    }
    else if (!currency.equals(other.currency))
      return false;
    return true;
  }

}

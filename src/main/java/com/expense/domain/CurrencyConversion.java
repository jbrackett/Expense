package com.expense.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

@Entity
@Table
public class CurrencyConversion {

  @Id
  @GeneratedValue
  private Long id;
  private Currency original;
  private Currency wanted;
  private BigDecimal rate;
  @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
  private LocalDate conversionDate;

  public CurrencyConversion() {
    original = Currency.getInstance("USD");
    wanted = Currency.getInstance("USD");
    rate = BigDecimal.ZERO.setScale(7, RoundingMode.HALF_UP);
    conversionDate = LocalDate.now();
  }

  public CurrencyConversion(Currency original, Currency wanted,
      BigDecimal rate, LocalDate conversionDate) {
    this.original = original;
    this.wanted = wanted;
    this.rate = rate;
    this.conversionDate = conversionDate;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Currency getOriginal() {
    return original;
  }

  public void setOriginal(Currency original) {
    this.original = original;
  }

  public Currency getWanted() {
    return wanted;
  }

  public void setWanted(Currency wanted) {
    this.wanted = wanted;
  }

  public BigDecimal getRate() {
    return rate;
  }

  public void setRate(BigDecimal rate) {
    this.rate = rate;
  }

  public LocalDate getConversionDate() {
    return conversionDate;
  }

  public void setConversionDate(LocalDate conversionDate) {
    this.conversionDate = conversionDate;
  }

  @Override
  public String toString() {
    return "CurrencyConversion [id=" + id + ", original=" + original
        + ", wanted=" + wanted + ", rate=" + rate + ", conversionDate="
        + conversionDate + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result
        + ((conversionDate == null) ? 0 : conversionDate.hashCode());
    result = prime * result + ((original == null) ? 0 : original.hashCode());
    result = prime * result + ((rate == null) ? 0 : rate.hashCode());
    result = prime * result + ((wanted == null) ? 0 : wanted.hashCode());
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
    CurrencyConversion other = (CurrencyConversion) obj;
    if (conversionDate == null) {
      if (other.conversionDate != null)
        return false;
    }
    else if (!conversionDate.equals(other.conversionDate))
      return false;
    if (original == null) {
      if (other.original != null)
        return false;
    }
    else if (!original.equals(other.original))
      return false;
    if (rate == null) {
      if (other.rate != null)
        return false;
    }
    else if (rate.compareTo(other.rate) != 0)
      return false;
    if (wanted == null) {
      if (other.wanted != null)
        return false;
    }
    else if (!wanted.equals(other.wanted))
      return false;
    return true;
  }
}

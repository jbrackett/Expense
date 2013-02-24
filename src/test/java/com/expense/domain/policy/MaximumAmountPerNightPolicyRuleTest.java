package com.expense.domain.policy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Currency;

import org.joda.time.LocalDate;
import org.junit.Test;

import com.expense.domain.Expense;
import com.expense.domain.embed.Money;

public class MaximumAmountPerNightPolicyRuleTest {

  @Test
  public void getConvertedValue() {
    MaximumAmountPerNightPolicyRule rule = new MaximumAmountPerNightPolicyRule();

    rule.setRuleValue(null);
    assertEquals(BigDecimal.ZERO, rule.getConvertedRuleValue());

    rule.setRuleValue("blah");
    assertEquals(BigDecimal.ZERO, rule.getConvertedRuleValue());

    rule.setRuleValue("1");
    assertEquals(new BigDecimal("1"), rule.getConvertedRuleValue());

    rule.setRuleValue("123.45");
    assertEquals(new BigDecimal("123.45"), rule.getConvertedRuleValue());
  }

  @Test
  public void isValidWithStartDateOnly() {
    MaximumAmountPerNightPolicyRule rule = new MaximumAmountPerNightPolicyRule();
    rule.setRuleValue("1");

    Expense expense = new Expense();
    expense.setStartDate(LocalDate.now());

    expense.setMoney(new Money(BigDecimal.ZERO, Currency.getInstance("USD")));
    assertTrue(rule.isValid(expense));

    expense.setMoney(new Money(new BigDecimal("0.99"), Currency
        .getInstance("USD")));
    assertTrue(rule.isValid(expense));

    expense.setMoney(new Money(BigDecimal.ONE, Currency.getInstance("USD")));
    assertTrue(rule.isValid(expense));

    expense.setMoney(new Money(new BigDecimal("1.01"), Currency
        .getInstance("USD")));
    assertFalse(rule.isValid(expense));

    expense.setMoney(new Money(BigDecimal.TEN, Currency.getInstance("USD")));
    assertFalse(rule.isValid(expense));
  }

  @Test
  public void isValidWithStartAndEndDates() {
    MaximumAmountPerNightPolicyRule rule = new MaximumAmountPerNightPolicyRule();
    rule.setRuleValue("1");

    Expense expense = new Expense();
    expense.setStartDate(LocalDate.now());
    expense.setEndDate(LocalDate.now().plusDays(2));

    expense.setMoney(new Money(BigDecimal.ZERO, Currency.getInstance("USD")));
    assertTrue(rule.isValid(expense));

    expense.setMoney(new Money(new BigDecimal("0.99"), Currency
        .getInstance("USD")));
    assertTrue(rule.isValid(expense));

    expense.setMoney(new Money(BigDecimal.ONE, Currency.getInstance("USD")));
    assertTrue(rule.isValid(expense));

    expense.setMoney(new Money(new BigDecimal("1.99"), Currency
        .getInstance("USD")));
    assertTrue(rule.isValid(expense));

    expense
        .setMoney(new Money(new BigDecimal("2"), Currency.getInstance("USD")));
    assertTrue(rule.isValid(expense));

    expense.setMoney(new Money(new BigDecimal("2.01"), Currency
        .getInstance("USD")));
    assertFalse(rule.isValid(expense));

    expense.setMoney(new Money(BigDecimal.TEN, Currency.getInstance("USD")));
    assertFalse(rule.isValid(expense));
  }

  @Test
  public void getInvalidMessage() {
    MaximumAmountPerNightPolicyRule rule = new MaximumAmountPerNightPolicyRule();
    rule.setRuleValue("1");

    Expense expense = new Expense();
    expense.setMoney(new Money(BigDecimal.TEN, Currency.getInstance("USD")));

    String expected = String.format(
        "%s is greater than the allowed amount of %s per night.", expense
            .getMoney().getAmount(), rule.getRuleValue());
    assertEquals(expected, rule.getInvalidMessage(expense));
  }

}

package com.expense.domain.policy;

import java.math.BigDecimal;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.apache.commons.lang3.math.NumberUtils;
import org.joda.time.Days;
import org.joda.time.LocalDate;

import com.expense.domain.Expense;
import com.expense.domain.PolicyRule;
import com.expense.domain.embed.Money;

@Entity
@DiscriminatorValue(MaximumAmountPerNightPolicyRule.POLICY_NAME)
public class MaximumAmountPerNightPolicyRule extends PolicyRule<BigDecimal> {

  public static final String POLICY_NAME = "MAXIMUM_AMOUNT_PER_NIGHT";

  public MaximumAmountPerNightPolicyRule() {
    super();
    name = getName();
    description = "Expense is out of policy when it's amount is greater than %i per night.";
  }

  @Override
  public String getName() {
    return POLICY_NAME;
  }

  @Override
  public boolean isValid(Expense expense) {
    int numberOfNights = 1;
    LocalDate start = expense.getStartDate();
    LocalDate end = expense.getEndDate();

    if (end == null) {
      numberOfNights = 1;
    }
    else {
      Days days = Days.daysBetween(start, end);
      if (days.getDays() > 1) {
        numberOfNights = days.getDays();
      }
      else {
        numberOfNights = 1;
      }
    }

    Money expenseAmount = expense.getMoney();
    Money nightlyAmount = expenseAmount.divide(numberOfNights);
    return nightlyAmount.compareTo(getConvertedRuleValue()) <= 0;
  }

  @Override
  public BigDecimal getConvertedRuleValue() {
    if (isValueValid()) {
      return new BigDecimal(ruleValue);
    }
    return BigDecimal.ZERO;
  }

  @Override
  public boolean isValueValid() {
    return NumberUtils.isNumber(ruleValue);
  }

  @Override
  public String getInvalidMessage(Expense expense) {
    return String.format(
        "%s is greater than the allowed amount of %s per night.", expense
            .getMoney().getAmount(), ruleValue);
  }

  @Override
  public String getDescription() {
    return description;
  }
}

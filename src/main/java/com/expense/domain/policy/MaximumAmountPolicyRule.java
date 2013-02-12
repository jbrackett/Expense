package com.expense.domain.policy;

import java.math.BigDecimal;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.apache.commons.lang3.math.NumberUtils;

import com.expense.domain.Expense;
import com.expense.domain.PolicyRule;
import com.expense.domain.embed.Money;

@Entity
@DiscriminatorValue(MaximumAmountPolicyRule.POLICY_NAME)
public class MaximumAmountPolicyRule extends PolicyRule<BigDecimal> {

  public static final String POLICY_NAME = "MAXIMUM_AMOUNT";

  public MaximumAmountPolicyRule() {
    super();
    name = getName();
  }

  @Override
  public String getName() {
    return POLICY_NAME;
  }

  @Override
  public boolean isValid(Expense expense) {
    Money expenseMoney = expense.getMoney();
    BigDecimal maximum = getConvertedRuleValue();
    return expenseMoney.compareTo(maximum) != 1;
  }

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
    return String.format("%s is greater than the allowed amount of %s", expense
        .getMoney().getAmount(), ruleValue);
  }

}

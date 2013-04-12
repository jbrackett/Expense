package com.expense.domain.policy;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.expense.domain.PolicyRule;

public enum StandardRules {

  INSTANCE;

  private static final List<PolicyRule<?>> STANDARD_RULES = Arrays
      .<PolicyRule<?>> asList(new MaximumAmountPolicyRule());

  public List<PolicyRule<?>> getStandardRules() {
    return Collections.unmodifiableList(STANDARD_RULES);
  }

}

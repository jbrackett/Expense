package com.expense.domain.category;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.expense.domain.Category;
import com.expense.domain.PolicyRule;
import com.expense.domain.constants.CategoryType;
import com.expense.domain.policy.MaximumAmountPolicyRule;

@Entity
@DiscriminatorValue(CategoryType.STANDARD)
public class StandardCategory extends Category {

  public static final List<PolicyRule<?>> STANDARD_RULES = new ArrayList<>();

  static {
    STANDARD_RULES.add(new MaximumAmountPolicyRule());
  }

  public StandardCategory() {
    super();
    categoryType = CategoryType.STANDARD;
  }

  @Override
  public List<PolicyRule<?>> getAvailablePolicyRules() {
    return StandardCategory.STANDARD_RULES;
  }

}

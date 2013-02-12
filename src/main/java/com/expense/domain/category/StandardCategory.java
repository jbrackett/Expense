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

  public static final List<PolicyRule<? extends Object>> STANDARD_RULES = new ArrayList<>();

  static {
    STANDARD_RULES.add(new MaximumAmountPolicyRule());
  }

  public StandardCategory() {
    super();
    categoryType = getCategoryType();
  }

  @Override
  public String getCategoryType() {
    return CategoryType.STANDARD;
  }

  @Override
  public List<PolicyRule<? extends Object>> getAvailablePolicyRules() {
    return StandardCategory.STANDARD_RULES;
  }

}

package com.expense.domain.category;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.expense.domain.Category;
import com.expense.domain.PolicyRule;
import com.expense.domain.constants.CategoryType;
import com.expense.domain.policy.MaximumAmountPerNightPolicyRule;

@Entity
@DiscriminatorValue(CategoryType.LODGING)
public class LodgingCategory extends Category {

  public LodgingCategory() {
    super();
    categoryType = CategoryType.LODGING;
  }

  @Override
  public List<PolicyRule<?>> getReleventPolicyRules() {
    List<PolicyRule<?>> rules = new ArrayList<>();

    rules.add(new MaximumAmountPerNightPolicyRule());
    rules.addAll(StandardCategory.STANDARD_RULES);

    return rules;
  }

}

package com.expense.domain.category;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.expense.domain.Category;
import com.expense.domain.PolicyRule;
import com.expense.domain.constants.CategoryType;

@Entity
@DiscriminatorValue(CategoryType.AIR)
public class AirCategory extends Category {

  public AirCategory() {
    super();
    categoryType = CategoryType.AIR;
  }

  @Override
  public List<PolicyRule<?>> getAvailablePolicyRules() {
    return StandardCategory.STANDARD_RULES;
  }

}

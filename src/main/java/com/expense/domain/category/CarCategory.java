package com.expense.domain.category;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.expense.domain.Category;
import com.expense.domain.PolicyRule;
import com.expense.domain.constants.CategoryType;

@Entity
@DiscriminatorValue(CategoryType.CAR)
public class CarCategory extends Category {

  public CarCategory() {
    super();
    categoryType = getCategoryType();
  }

  @Override
  public String getCategoryType() {
    return CategoryType.CAR;
  }

  @Override
  public List<PolicyRule<?>> getAvailablePolicyRules() {
    return StandardCategory.STANDARD_RULES;
  }

}

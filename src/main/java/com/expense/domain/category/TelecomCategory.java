package com.expense.domain.category;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.expense.domain.Category;
import com.expense.domain.PolicyRule;
import com.expense.domain.constants.CategoryType;

@Entity
@DiscriminatorValue(CategoryType.TELECOM)
public class TelecomCategory extends Category {

  public TelecomCategory() {
    super();
    categoryType = getCategoryType();
  }

  @Override
  public String getCategoryType() {
    return CategoryType.TELECOM;
  }

  @Override
  public List<PolicyRule<?>> getAvailablePolicyRules() {
    return StandardCategory.STANDARD_RULES;
  }

}

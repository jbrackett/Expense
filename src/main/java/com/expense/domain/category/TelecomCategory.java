package com.expense.domain.category;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.expense.domain.Category;
import com.expense.domain.PolicyRule;
import com.expense.domain.constants.CategoryType;
import com.expense.domain.policy.StandardRules;

@Entity
@DiscriminatorValue(CategoryType.TELECOM)
public class TelecomCategory extends Category {

  public TelecomCategory() {
    super();
    categoryType = CategoryType.TELECOM;
  }

  @Override
  public List<PolicyRule<?>> getReleventPolicyRules() {
    return StandardRules.INSTANCE.getStandardRules();
  }

}

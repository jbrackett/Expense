package com.expense.domain.category;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.expense.domain.Category;

public class LodgingCategoryTest {

  @Test
  public void getPolicyRules() {
    Category category = new LodgingCategory();
    assertEquals(2, category.getReleventPolicyRules().size());
    assertEquals(2, category.getAvailablePolicyRules().size());
    assertEquals(0, category.getPolicyRules().size());

    category.addPolicyRule(category.getAvailablePolicyRules().get(0));
    assertEquals(2, category.getReleventPolicyRules().size());
    assertEquals(1, category.getAvailablePolicyRules().size());
    assertEquals(1, category.getPolicyRules().size());

    category.addPolicyRule(category.getAvailablePolicyRules().get(0));
    assertEquals(2, category.getReleventPolicyRules().size());
    assertEquals(0, category.getAvailablePolicyRules().size());
    assertEquals(2, category.getPolicyRules().size());
  }

}

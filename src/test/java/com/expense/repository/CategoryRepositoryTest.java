package com.expense.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.expense.config.AppConfig;
import com.expense.domain.Category;
import com.expense.domain.category.StandardCategory;
import com.expense.domain.policy.MaximumAmountPolicyRule;

@WebAppConfiguration
@ContextConfiguration(classes = AppConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class CategoryRepositoryTest {

  @Resource
  public CategoryRepository categoryRepository;

  @Before
  public void cleanup() {
    categoryRepository.deleteAll();
    assertEquals(0L, categoryRepository.count());
  }

  @Test
  public void saveAndFind() {
    Category category = new StandardCategory();
    Category saved = categoryRepository.save(category);

    assertNotNull(saved.getId());
    Category found = categoryRepository.findOne(saved.getId());
    assertEquals(saved, found);
  }

  @Test
  public void addPolicyRule() {
    Category category = new StandardCategory();
    category.addPolicyRule(new MaximumAmountPolicyRule());
    Category saved = categoryRepository.save(category);

    assertNotNull(saved.getId());
    Category found = categoryRepository.findOne(saved.getId());
    assertEquals(saved, found);
    assertEquals(1, found.getPolicyRules().size());
  }

}

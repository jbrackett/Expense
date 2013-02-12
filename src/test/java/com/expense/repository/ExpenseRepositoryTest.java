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
import com.expense.domain.Expense;
import com.expense.domain.category.StandardCategory;

@WebAppConfiguration
@ContextConfiguration(classes = AppConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class ExpenseRepositoryTest {

  @Resource
  public CategoryRepository categoryRepository;
  @Resource
  public ExpenseRepository expenseRepository;

  @Before
  public void cleanup() {
    categoryRepository.deleteAll();
    assertEquals(0L, categoryRepository.count());
    expenseRepository.deleteAll();
    assertEquals(0L, expenseRepository.count());
  }

  @Test
  public void saveAndFind() {
    Category category = new StandardCategory();
    Category savedCategory = categoryRepository.save(category);

    Expense expense = new Expense();
    expense.setCategory(savedCategory);
    Expense saved = expenseRepository.save(expense);

    assertNotNull(saved.getId());
    Expense found = expenseRepository.findOne(saved.getId());
    assertEquals(saved, found);
  }

}

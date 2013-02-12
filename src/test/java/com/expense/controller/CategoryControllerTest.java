package com.expense.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.expense.domain.Category;
import com.expense.domain.category.StandardCategory;
import com.expense.repository.CategoryRepository;

@RunWith(MockitoJUnitRunner.class)
public class CategoryControllerTest {

  private MockMvc mockMvc;

  @Mock
  private CategoryRepository categoryRepository;

  @Before
  public void loadUser() {
    mockMvc = MockMvcBuilders.standaloneSetup(
        new CategoryController(categoryRepository)).build();
    Mockito.reset(categoryRepository);
  }

  @Test
  public void getById() throws Exception {
    Long id = 1L;
    Category category = new StandardCategory();
    category.setId(id);

    when(categoryRepository.findOne(id)).thenReturn(category);
    mockMvc.perform(get("/category/{id}", id)).andExpect(status().isOk())
        .andExpect(jsonPath("$id").value(category.getId().intValue()));
  }
}

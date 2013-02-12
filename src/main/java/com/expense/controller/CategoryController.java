package com.expense.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.expense.domain.Category;
import com.expense.repository.CategoryRepository;

@Controller
@RequestMapping("/category")
public class CategoryController {

  private final CategoryRepository categoryRepository;

  @Autowired
  public CategoryController(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  @RequestMapping(method = RequestMethod.GET)
  @ResponseBody
  public Iterable<Category> allCategories() {
    return categoryRepository.findAll();
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  @ResponseBody
  public Category getCategory(@PathVariable("id") Long id) {
    return categoryRepository.findOne(id);
  }

  @RequestMapping(method = RequestMethod.POST)
  @ResponseBody
  public Long createCategory(@RequestBody Category category) {
    Category saved = categoryRepository.save(category);
    return saved.getId();
  }

  @RequestMapping(method = RequestMethod.PUT)
  @ResponseBody
  public void updateCategory(@RequestBody Category category) {
    categoryRepository.save(category);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  @ResponseBody
  public void deleteCategory(@PathVariable("id") Long id) {
    Category category = categoryRepository.findOne(id);
    categoryRepository.delete(category);
  }

}

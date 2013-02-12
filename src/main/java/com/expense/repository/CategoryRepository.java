package com.expense.repository;

import org.springframework.data.repository.CrudRepository;

import com.expense.domain.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {

}

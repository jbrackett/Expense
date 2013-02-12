package com.expense.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.history.RevisionRepository;

import com.expense.domain.Category;

public interface CategoryRepository extends CrudRepository<Category, Long>,
    RevisionRepository<Category, Long, Integer> {

}

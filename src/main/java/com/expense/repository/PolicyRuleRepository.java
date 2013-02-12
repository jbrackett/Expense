package com.expense.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.history.RevisionRepository;

import com.expense.domain.PolicyRule;

public interface PolicyRuleRepository extends
    CrudRepository<PolicyRule<?>, Long>,
    RevisionRepository<PolicyRule<?>, Long, Integer> {

}

package com.expense.repository;

import org.springframework.data.repository.CrudRepository;

import com.expense.domain.PolicyRule;

public interface PolicyRuleRepository extends
    CrudRepository<PolicyRule<?>, Long> {

}

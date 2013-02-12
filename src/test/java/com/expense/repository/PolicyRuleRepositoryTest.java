package com.expense.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.expense.config.AppConfig;
import com.expense.domain.PolicyRule;
import com.expense.domain.policy.MaximumAmountPolicyRule;

@WebAppConfiguration
@ContextConfiguration(classes = AppConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class PolicyRuleRepositoryTest {

  @Resource
  private PolicyRuleRepository policyRuleRepository;

  @Before
  public void cleanup() {
    policyRuleRepository.deleteAll();
    assertEquals(0L, policyRuleRepository.count());
  }

  @Test
  public void saveAndFind() {
    PolicyRule<BigDecimal> policyRule = new MaximumAmountPolicyRule();
    PolicyRule<BigDecimal> saved = policyRuleRepository.save(policyRule);

    assertNotNull(saved.getId());
    PolicyRule<?> found = policyRuleRepository.findOne(saved.getId());
    assertEquals(saved, found);
  }

}

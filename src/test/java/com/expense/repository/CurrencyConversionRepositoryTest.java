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
import com.expense.domain.CurrencyConversion;

@WebAppConfiguration
@ContextConfiguration(classes = AppConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class CurrencyConversionRepositoryTest {

  @Resource
  CurrencyConversionRepository currencyConversionRepository;

  @Before
  public void cleanup() {
    currencyConversionRepository.deleteAll();
    assertEquals(0L, currencyConversionRepository.count());
  }

  @Test
  public void saveAndFind() {
    CurrencyConversion conversion = new CurrencyConversion();
    CurrencyConversion saved = currencyConversionRepository.save(conversion);

    assertNotNull(saved.getId());

    CurrencyConversion found = currencyConversionRepository.findOne(saved
        .getId());
    assertEquals(saved, found);
  }

}

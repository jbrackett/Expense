package com.expense.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "com.expense.controller")
@Import(AppConfig.class)
public class WebConfig extends WebMvcConfigurerAdapter {

  @Bean
  public InternalResourceViewResolver setupViewResolver() {
    InternalResourceViewResolver resolver = new InternalResourceViewResolver();
    resolver.setPrefix("/WEB-INF/views/");

    return resolver;
  }

  @Override
  public void configureDefaultServletHandling(
      DefaultServletHandlerConfigurer configurer) {
    configurer.enable();
  }

  @Override
  public void configureMessageConverters(
      List<HttpMessageConverter<?>> converters) {

    MappingJackson2HttpMessageConverter jacksonConverter = new MappingJackson2HttpMessageConverter();

    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
        false);
    objectMapper.registerModule(new JodaModule());
    jacksonConverter.setObjectMapper(objectMapper);

    converters.add(jacksonConverter);
  }

}

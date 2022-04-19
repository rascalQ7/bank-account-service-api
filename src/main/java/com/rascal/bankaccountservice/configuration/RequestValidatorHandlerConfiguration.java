package com.rascal.bankaccountservice.configuration;

import com.rascal.bankaccountservice.controller.transaction.validator.RequestValidatorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RequestValidatorHandlerConfiguration {

  @Bean
  RequestValidatorHandler requestValidatorHandler() {
    return new RequestValidatorHandler();
  }
}

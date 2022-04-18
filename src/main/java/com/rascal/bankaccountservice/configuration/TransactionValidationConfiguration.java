package com.rascal.bankaccountservice.configuration;

import com.rascal.bankaccountservice.service.transaction.validator.CurrencyValidator;
import com.rascal.bankaccountservice.service.transaction.validator.SufficientBalanceValidator;
import com.rascal.bankaccountservice.service.transaction.validator.TransactionValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransactionValidationConfiguration {

  @Bean("creditValidator")
  TransactionValidator creditValidator() {
    var sufficientBalanceValidator = new SufficientBalanceValidator(null);
    return new CurrencyValidator(sufficientBalanceValidator);
  }

  @Bean("debitValidator")
  TransactionValidator debitValidator() {
    return new CurrencyValidator(null);
  }
}

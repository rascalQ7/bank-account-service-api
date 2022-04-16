package com.rascal.bankaccountservice.controller.transaction.validator.currency;

import java.util.Currency;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CurrencyValidator implements ConstraintValidator<CurrencyConstraint, String> {

  @Override
  public void initialize(CurrencyConstraint constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    try {
      Currency.getInstance(value);
    } catch (Exception e) {
      return false;
    }
    return true;
  }
}

package com.rascal.bankaccountservice.controller.transaction.validator.transactiontype;

import com.rascal.bankaccountservice.domain.transaction.TransactionType;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TransactionTypeValidator implements
    ConstraintValidator<TransactionTypeConstraint, String> {

  @Override
  public void initialize(TransactionTypeConstraint constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    try {
      TransactionType.valueOf(value);
    } catch (Exception e) {
      return false;
    }
    return true;
  }
}

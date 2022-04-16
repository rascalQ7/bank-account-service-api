package com.rascal.bankaccountservice.controller.transaction.validator.amount;

import java.math.BigDecimal;
import java.util.Currency;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class AmountValidator implements ConstraintValidator<AmountConstraint, Object> {

  private String field;
  private String dependency;

  @Override
  public void initialize(AmountConstraint constraintAnnotation) {
    this.field = constraintAnnotation.field();
    this.dependency = constraintAnnotation.dependency();
  }

  @Override
  public boolean isValid(Object value, ConstraintValidatorContext context) {
    int maxDecimalPlaces;
    int actualDecimalPlaces;

    try {
      String dependencyValue = (String) new BeanWrapperImpl(value).getPropertyValue(dependency);
      var currency = Currency.getInstance(dependencyValue);
      maxDecimalPlaces = currency.getDefaultFractionDigits();
    } catch (Exception e) {
      return true;
    }

    try {
      Double amount = (Double) new BeanWrapperImpl(value).getPropertyValue(field);
      actualDecimalPlaces = new BigDecimal(amount.toString()).scale();
    } catch (Exception e) {
      return false;
    }
    return actualDecimalPlaces <= maxDecimalPlaces;
  }
}
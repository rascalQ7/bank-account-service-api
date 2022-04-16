package com.rascal.bankaccountservice.controller.transaction.validator.amount;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = AmountValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface AmountConstraint {

  String message() default "Fields values don't match!";

  String field();

  String dependency();

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  @Target({ElementType.TYPE})
  @Retention(RetentionPolicy.RUNTIME)
  @interface List {

    AmountConstraint[] value();
  }
}
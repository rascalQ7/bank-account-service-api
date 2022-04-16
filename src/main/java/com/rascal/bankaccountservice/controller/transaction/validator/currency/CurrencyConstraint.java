package com.rascal.bankaccountservice.controller.transaction.validator.currency;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = CurrencyValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CurrencyConstraint {

  String message() default "must follow ISO 4217";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}


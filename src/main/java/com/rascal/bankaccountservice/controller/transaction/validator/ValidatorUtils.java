package com.rascal.bankaccountservice.controller.transaction.validator;

import com.rascal.bankaccountservice.exception.BadRequestException;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;

public class ValidatorUtils {

  private ValidatorUtils() {
  }

  public static void handleRequestValidationResult(BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      var requestValidationMessages =
          bindingResult.getGlobalErrors().stream()
              .map(DefaultMessageSourceResolvable::getDefaultMessage);
      var requestFieldValidationMessages =
          bindingResult.getFieldErrors().stream()
              .map(e -> e.getField() + ": " + e.getDefaultMessage());

      var errorMessage = Stream.concat(requestValidationMessages, requestFieldValidationMessages)
          .collect(Collectors.joining("; "));
      throw new BadRequestException(errorMessage);
    }
  }
}

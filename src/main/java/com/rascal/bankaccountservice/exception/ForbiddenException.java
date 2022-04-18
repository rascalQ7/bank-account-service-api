package com.rascal.bankaccountservice.exception;

public class ForbiddenException extends RuntimeException {

  public ForbiddenException(String errorMessage) {
    super(errorMessage);
  }
}

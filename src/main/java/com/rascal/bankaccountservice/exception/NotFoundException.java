package com.rascal.bankaccountservice.exception;

public class NotFoundException extends RuntimeException {

  public NotFoundException(String errorMessage) {
    super(errorMessage);
  }
}

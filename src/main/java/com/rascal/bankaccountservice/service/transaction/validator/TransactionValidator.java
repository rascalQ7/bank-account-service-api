package com.rascal.bankaccountservice.service.transaction.validator;

import com.rascal.bankaccountservice.domain.transaction.Transaction;
import com.rascal.bankaccountservice.persistance.AccountEntity;

public abstract class TransactionValidator {

  public TransactionValidator nextValidator;

  protected TransactionValidator(TransactionValidator nextValidator) {
    this.nextValidator = nextValidator;
  }

  public abstract void validate(Transaction transaction, AccountEntity accountEntity);
}

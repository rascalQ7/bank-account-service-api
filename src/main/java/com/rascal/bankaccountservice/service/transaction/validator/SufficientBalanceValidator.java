package com.rascal.bankaccountservice.service.transaction.validator;

import com.rascal.bankaccountservice.domain.transaction.Transaction;
import com.rascal.bankaccountservice.exception.ForbiddenException;
import com.rascal.bankaccountservice.persistance.AccountEntity;

public class SufficientBalanceValidator extends TransactionValidator {

  public SufficientBalanceValidator(TransactionValidator nextProcessor) {
    super(nextProcessor);
  }

  @Override
  public void validate(Transaction transaction, AccountEntity accountEntity) {
    if (transaction.amount() > accountEntity.getBalance()) {
      throw new ForbiddenException("Insufficient balance");
    }
    if (nextProcessor != null) {
      nextProcessor.validate(transaction, accountEntity);
    }
  }
}

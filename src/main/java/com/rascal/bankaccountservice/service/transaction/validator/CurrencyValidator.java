package com.rascal.bankaccountservice.service.transaction.validator;

import com.rascal.bankaccountservice.domain.transaction.Transaction;
import com.rascal.bankaccountservice.exception.ForbiddenException;
import com.rascal.bankaccountservice.persistance.AccountEntity;

public class CurrencyValidator extends TransactionValidator {

  public CurrencyValidator(TransactionValidator nextProcessor) {
    super(nextProcessor);
  }

  @Override
  public void validate(Transaction transaction, AccountEntity accountEntity) {
    if (transaction.currency().getNumericCode() != accountEntity.getCurrency()) {
      throw new ForbiddenException(
          "Account does not allow currency " + transaction.currency().getCurrencyCode()
      );
    }
    if (nextProcessor != null) {
      nextProcessor.validate(transaction, accountEntity);
    }
  }
}

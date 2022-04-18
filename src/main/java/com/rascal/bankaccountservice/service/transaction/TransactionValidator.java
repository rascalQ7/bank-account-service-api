package com.rascal.bankaccountservice.service.transaction;

import com.rascal.bankaccountservice.exception.ForbiddenException;
import java.util.Currency;

public class TransactionValidator {

  public static void validateBalanceSufficient(Double transactionAmount, Double balanceAmount) {
    if (transactionAmount > balanceAmount) {
      throw new ForbiddenException("Insufficient balance");
    }
  }

  public static void validateAccountTransactionCurrency(Currency transaction, int accountCurrency) {
    if (transaction.getNumericCode() != accountCurrency) {
      throw new ForbiddenException(
          "Account does not allow currency " + transaction.getCurrencyCode()
      );
    }
  }
}

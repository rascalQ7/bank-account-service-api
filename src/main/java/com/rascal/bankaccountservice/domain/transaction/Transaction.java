package com.rascal.bankaccountservice.domain.transaction;

import com.rascal.bankaccountservice.controller.transaction.TransactionRequest;
import java.util.Currency;


public record Transaction(
    String accountNumber,
    Double amount,
    Currency currency,
    TransactionType transactionType
) {

  public static Transaction of(TransactionRequest transactionRequest) {
    return new Transaction(
        transactionRequest.getAccountNumber(),
        transactionRequest.getAmount(),
        Currency.getInstance(transactionRequest.getCurrency()),
        TransactionType.valueOf(transactionRequest.getTransactionType())
    );
  }
}

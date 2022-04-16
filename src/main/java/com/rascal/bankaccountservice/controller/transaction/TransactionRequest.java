package com.rascal.bankaccountservice.controller.transaction;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.rascal.bankaccountservice.controller.transaction.validator.amount.AmountConstraint;
import com.rascal.bankaccountservice.controller.transaction.validator.currency.CurrencyConstraint;
import com.rascal.bankaccountservice.controller.transaction.validator.transactiontype.TransactionTypeConstraint;
import javax.validation.constraints.NotBlank;

@AmountConstraint(field = "amount", dependency = "currency")
public record TransactionRequest(
    @NotBlank String accountNumber,
    Double amount,
    @CurrencyConstraint String currency,
    @TransactionTypeConstraint String transactionType
) {

  @JsonCreator
  public TransactionRequest(
      String accountNumber,
      Double amount,
      String currency,
      String transactionType
  ) {
    this.accountNumber = accountNumber;
    this.amount = amount;
    this.currency = currency;
    this.transactionType = transactionType;
  }
}

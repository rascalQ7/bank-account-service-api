package com.rascal.bankaccountservice.domain.transaction;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Currency;
import java.util.Objects;


public class Transaction {

  private final String accountNumber;
  private final Double amount;
  private final Currency currency;
  private final TransactionType transactionType;

  @JsonCreator
  public Transaction(
      String accountNumber,
      Double amount,
      String currency,
      String transactionType
  ) {
    this.accountNumber = Objects.requireNonNull(accountNumber);
    this.amount = Objects.requireNonNull(amount);
    Objects.requireNonNull(currency);
    this.currency = Currency.getInstance(currency);
    System.out.println(this.currency.getNumericCode());
    Objects.requireNonNull(transactionType);
    this.transactionType = TransactionType.valueOf(transactionType);
  }

  @Override
  public String toString() {
    return "Transaction{" +
        "accountNumber='" + accountNumber + '\'' +
        ", amount=" + amount +
        ", currency=" + currency +
        ", transactionType=" + transactionType +
        '}';
  }
}

package com.rascal.bankaccountservice.service.transaction;

import com.rascal.bankaccountservice.domain.transaction.Transaction;
import com.rascal.bankaccountservice.domain.transaction.TransactionType;

public interface TransactionService {

  TransactionType getType();

  void book(Transaction transaction);

}

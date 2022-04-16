package com.rascal.bankaccountservice.service.transaction;

import com.rascal.bankaccountservice.domain.transaction.Transaction;
import com.rascal.bankaccountservice.domain.transaction.TransactionType;
import org.springframework.stereotype.Service;

@Service
public class DebitTransactionService implements TransactionService{

  @Override
  public TransactionType getType() {
    return TransactionType.DEBIT;
  }

  @Override
  public void book(Transaction transaction) {

  }
}

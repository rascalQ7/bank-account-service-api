package com.rascal.bankaccountservice.service.transaction;

import com.rascal.bankaccountservice.domain.account.AccountStatus;
import com.rascal.bankaccountservice.domain.transaction.Transaction;
import com.rascal.bankaccountservice.domain.transaction.TransactionType;
import com.rascal.bankaccountservice.exception.NotFoundException;
import com.rascal.bankaccountservice.persistance.AccountRepository;
import java.util.Objects;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CreditTransactionService implements TransactionService {

  private final AccountRepository accountRepository;

  public CreditTransactionService(AccountRepository accountRepository) {
    this.accountRepository = Objects.requireNonNull(accountRepository);
  }

  @Override
  public TransactionType getType() {
    return TransactionType.CREDIT;
  }

  @Override
  @Transactional
  public void book(Transaction transaction) {
    var accountEntity = accountRepository.findByAccountNumberAndAccountStatus(
        transaction.accountNumber(),
        AccountStatus.OPEN.name()
    ).orElseThrow(() -> new NotFoundException(
        "No active account " + transaction.accountNumber() + " was found")
    );
    TransactionValidator
        .validateBalanceSufficient(transaction.amount(), accountEntity.getBalance());
    TransactionValidator
        .validateAccountTransactionCurrency(transaction.currency(), accountEntity.getCurrency());
    accountEntity.setBalance(accountEntity.getBalance() - transaction.amount());
    accountRepository.save(accountEntity);
  }
}

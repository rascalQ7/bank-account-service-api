package com.rascal.bankaccountservice.service.transaction;

import com.rascal.bankaccountservice.domain.account.AccountStatus;
import com.rascal.bankaccountservice.domain.transaction.Transaction;
import com.rascal.bankaccountservice.domain.transaction.TransactionType;
import com.rascal.bankaccountservice.exception.NotFoundException;
import com.rascal.bankaccountservice.persistance.AccountEntity;
import com.rascal.bankaccountservice.persistance.AccountRepository;
import com.rascal.bankaccountservice.service.transaction.validator.TransactionValidator;
import java.util.Objects;
import java.util.function.Function;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

@Service
public class CreditTransactionService implements TransactionService {

  private final AccountRepository accountRepository;
  private final TransactionValidator validator;

  public CreditTransactionService(
      AccountRepository accountRepository,
      @Qualifier("creditValidator") TransactionValidator validator
  ) {
    this.accountRepository = Objects.requireNonNull(accountRepository);
    this.validator = Objects.requireNonNull(validator);
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
    validator.validate(transaction, accountEntity);
    accountEntity.setBalance(accountEntity.getBalance() - transaction.amount());
    accountRepository.save(accountEntity);
  }
}

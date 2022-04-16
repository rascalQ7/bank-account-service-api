package com.rascal.bankaccountservice.service.account;

import com.rascal.bankaccountservice.domain.account.AccountState;
import com.rascal.bankaccountservice.persistance.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
  private final AccountRepository accountRepository;

  public AccountService(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  public AccountState getState(String accountNumber) {
    return AccountState.of(this.accountRepository.findByAccountNumber(accountNumber));
  }

}

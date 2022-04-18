package com.rascal.bankaccountservice.service.account;

import com.rascal.bankaccountservice.controller.account.AccountStateResponse;
import com.rascal.bankaccountservice.exception.NotFoundException;
import com.rascal.bankaccountservice.persistance.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public record AccountService(AccountRepository accountRepository) {

  public AccountStateResponse getState(String accountNumber) {
    return AccountStateResponse.of(
        this.accountRepository.findByAccountNumber(accountNumber)
            .orElseThrow(
                () -> new NotFoundException("Account " + accountNumber + " does not exist")
            )
    );
  }
}

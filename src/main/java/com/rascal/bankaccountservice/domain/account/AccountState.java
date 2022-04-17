package com.rascal.bankaccountservice.domain.account;

import com.rascal.bankaccountservice.persistance.AccountEntity;
import java.util.Currency;

public record AccountState(
    String accountNumber,
    Currency currency,
    Double balance,
    AccountStatus accountStatus
) {

  public static AccountState of(AccountEntity accountEntity) {
    return new AccountState(
        accountEntity.getAccountNumber(),
        Currency.getAvailableCurrencies().stream()
            .filter(c -> c.getNumericCode() == accountEntity.getCurrency())
            .findAny()
            .get(),
        accountEntity.getBalance(),
        AccountStatus.valueOf(accountEntity.getAccountStatus())
    );
  }
}

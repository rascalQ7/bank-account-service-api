package com.rascal.bankaccountservice.controller.account;

import com.rascal.bankaccountservice.domain.account.AccountStatus;
import com.rascal.bankaccountservice.persistance.AccountEntity;
import java.util.Currency;

public record AccountStateResponse(
    String accountNumber,
    Currency currency,
    Double balance,
    AccountStatus accountStatus
) {

  public static AccountStateResponse of(AccountEntity accountEntity) {
    return new AccountStateResponse(
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

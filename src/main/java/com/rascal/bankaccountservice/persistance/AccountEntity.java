package com.rascal.bankaccountservice.persistance;

import com.rascal.bankaccountservice.domain.account.AccountStatus;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AccountEntity {

  String accountNumber;
  int currency;
  Double balance;
  AccountStatus accountStatus;
  @Id
  private int id;

  public String getAccountNumber() {
    return accountNumber;
  }

  public int getCurrency() {
    return currency;
  }

  public Double getBalance() {
    return balance;
  }

  public AccountStatus getAccountStatus() {
    return accountStatus;
  }
}

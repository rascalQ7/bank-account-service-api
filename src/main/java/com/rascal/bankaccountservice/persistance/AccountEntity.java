package com.rascal.bankaccountservice.persistance;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AccountEntity {

  String accountNumber;
  Integer currency;
  Double balance;
  String accountStatus;
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  public String getAccountNumber() {
    return accountNumber;
  }

  public Integer getCurrency() {
    return currency;
  }

  public Double getBalance() {
    return balance;
  }

  public String getAccountStatus() {
    return accountStatus;
  }
}

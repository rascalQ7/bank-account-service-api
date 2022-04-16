package com.rascal.bankaccountservice.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Integer> {

  AccountEntity findByAccountNumber(String accountNumber);
}
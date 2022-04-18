package com.rascal.bankaccountservice.persistance;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<AccountEntity, Long> {

  Optional<AccountEntity> findByAccountNumber(String accountNumber);

  Optional<AccountEntity> findByAccountNumberAndAccountStatus(
      String accountNumber,
      String accountStatus
  );
}
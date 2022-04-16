package com.rascal.bankaccountservice.service.transaction;

import com.rascal.bankaccountservice.domain.transaction.TransactionType;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class TransactionServiceFactory {

  private final Map<TransactionType, TransactionService> transactionServiceMap;

  private TransactionServiceFactory(List<TransactionService> transactionServices) {
    this.transactionServiceMap = transactionServices.stream().collect(
        Collectors.toUnmodifiableMap(TransactionService::getType, Function.identity()));
  }

  public TransactionService getService(TransactionType transactionType) {
    return Optional.ofNullable(transactionServiceMap.get(transactionType))
        .orElseThrow(IllegalArgumentException::new);
  }
}

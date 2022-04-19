package com.rascal.bankaccountservice.domain.transaction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.rascal.bankaccountservice.controller.transaction.TransactionRequest;
import java.util.Currency;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TransactionTest {
  @Mock
  TransactionRequest transactionRequest;

  private Transaction transaction;

  @Test
  void shouldCreateTransaction() {
    when(transactionRequest.getTransactionType()).thenReturn("DEBIT");
    when(transactionRequest.getAmount()).thenReturn(0.01);
    when(transactionRequest.getAccountNumber()).thenReturn("account");
    when(transactionRequest.getCurrency()).thenReturn("EUR");
    transaction = Transaction.of(transactionRequest);

    assertEquals(TransactionType.DEBIT ,transaction.transactionType());
    assertEquals(transactionRequest.getAmount(), transaction.amount());
    assertEquals(transactionRequest.getAccountNumber(), transaction.accountNumber());
    assertEquals(Currency.getInstance(transactionRequest.getCurrency()), transaction.currency());
  }

}

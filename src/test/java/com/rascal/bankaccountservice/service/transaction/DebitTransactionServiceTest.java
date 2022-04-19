package com.rascal.bankaccountservice.service.transaction;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.when;

import com.rascal.bankaccountservice.domain.account.AccountStatus;
import com.rascal.bankaccountservice.domain.transaction.Transaction;
import com.rascal.bankaccountservice.exception.NotFoundException;
import com.rascal.bankaccountservice.persistance.AccountEntity;
import com.rascal.bankaccountservice.persistance.AccountRepository;
import com.rascal.bankaccountservice.service.transaction.validator.TransactionValidator;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DebitTransactionServiceTest {

  private static final String ACCOUNT = "account";
  @Mock
  private AccountRepository accountRepository;
  @Mock
  private TransactionValidator transactionValidator;
  @Mock
  private AccountEntity accountEntity;
  private Transaction transaction;
  @InjectMocks
  private DebitTransactionService transactionService;

  @BeforeEach
  void setUp() {
    transaction = new Transaction(ACCOUNT, 1.11, null, null);
  }

  @Test
  void shouldBookTransaction() {

    when(accountRepository.findByAccountNumberAndAccountStatus(ACCOUNT, AccountStatus.OPEN.name()))
        .thenReturn(Optional.of(accountEntity));

    transactionService.book(transaction);

    var inOrder = inOrder(accountRepository, transactionValidator, accountEntity);
    inOrder.verify(accountRepository)
        .findByAccountNumberAndAccountStatus(ACCOUNT, AccountStatus.OPEN.name());
    inOrder.verify(transactionValidator).validate(transaction, accountEntity);
    inOrder.verify(accountEntity).setBalance(any());
    inOrder.verify(accountRepository).save(accountEntity);
  }

  @Test
  void shouldThrowNotFoundExceptionWhenNoActiveAccountExist() {
    when(accountRepository.findByAccountNumberAndAccountStatus(ACCOUNT, AccountStatus.OPEN.name()))
        .thenReturn(Optional.empty());

    assertThrows(NotFoundException.class, () -> transactionService.book(transaction));
  }
}
package com.rascal.bankaccountservice.service.transaction.validator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.rascal.bankaccountservice.domain.transaction.Transaction;
import com.rascal.bankaccountservice.exception.ForbiddenException;
import com.rascal.bankaccountservice.persistance.AccountEntity;
import java.util.Currency;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SufficientBalanceValidatorTest {

  @Mock
  private TransactionValidator nextValidator;
  @Mock
  private AccountEntity accountEntity;
  private Transaction transaction;

  private SufficientBalanceValidator sufficientBalanceValidator;

  @BeforeEach
  void setUp() {
    transaction = new Transaction(null, 1.00, null, null);
  }

  @Test
  void shouldThrowForbiddenExceptionWhenInssuficientBalanceAgainstTransactionAmount() {
    sufficientBalanceValidator = new SufficientBalanceValidator(null);
    when(accountEntity.getBalance()).thenReturn(0.99);

    assertThrows(
        ForbiddenException.class, () -> sufficientBalanceValidator.validate(transaction, accountEntity)
    );
  }

  @Test
  void shouldValidateNextValidatorWhenValidatorIsNotNull() {
    sufficientBalanceValidator = new SufficientBalanceValidator(nextValidator);
    when(accountEntity.getBalance()).thenReturn(1.00);

    sufficientBalanceValidator.validate(transaction, accountEntity);

    verify(nextValidator).validate(transaction, accountEntity);
  }

  @Test
  void shouldNotValidateNextValidatorWhenValidatorIsNull() {
    sufficientBalanceValidator = new SufficientBalanceValidator(null);
    when(accountEntity.getBalance()).thenReturn(1.00);

    sufficientBalanceValidator.validate(transaction, accountEntity);

    verify(nextValidator, never()).validate(any(), any());
  }
}
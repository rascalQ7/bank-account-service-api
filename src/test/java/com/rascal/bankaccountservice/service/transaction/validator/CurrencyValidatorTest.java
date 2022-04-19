package com.rascal.bankaccountservice.service.transaction.validator;

import static org.junit.jupiter.api.Assertions.assertThrows;
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
class CurrencyValidatorTest {

  @Mock
  private TransactionValidator nextValidator;
  @Mock
  private AccountEntity accountEntity;
  private Transaction transaction;

  private CurrencyValidator currencyValidator;

  @BeforeEach
  void setUp() {
    transaction = new Transaction(null, null, Currency.getInstance("EUR"), null);
  }

  @Test
  void shouldThrowForbiddenExceptionWhenAccountAndTransactionCurrencyDiffers() {
    currencyValidator = new CurrencyValidator(null);
    when(accountEntity.getCurrency()).thenReturn(111);

    assertThrows(
        ForbiddenException.class, () -> currencyValidator.validate(transaction, accountEntity)
    );
  }

  @Test
  void shouldValidateNextValidatorWhenValidatorIsNotNull() {
    currencyValidator = new CurrencyValidator(nextValidator);
    when(accountEntity.getCurrency()).thenReturn(978);

    currencyValidator.validate(transaction, accountEntity);

    verify(nextValidator).validate(transaction, accountEntity);
  }

  @Test
  void shouldNotValidateNextValidatorWhenValidatorIsNull() {
    currencyValidator = new CurrencyValidator(null);
    when(accountEntity.getCurrency()).thenReturn(978);

    currencyValidator.validate(transaction, accountEntity);

    verify(nextValidator, never()).validate(any(), any());
  }
}
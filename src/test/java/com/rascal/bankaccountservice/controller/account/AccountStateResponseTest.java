package com.rascal.bankaccountservice.controller.account;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.rascal.bankaccountservice.domain.account.AccountStatus;
import com.rascal.bankaccountservice.persistance.AccountEntity;
import java.util.Currency;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AccountStateResponseTest {

  private static final String ACCOUNT = "account";
  private static final double BALANCE = 0.00;
  @Mock
  private AccountEntity accountEntity;
  private AccountStateResponse accountStateResponse;

  @Test
  void shouldCreateAccountStateResponse() {
    when(accountEntity.getAccountStatus()).thenReturn("OPEN");
    when(accountEntity.getCurrency()).thenReturn(978);
    when(accountEntity.getAccountNumber()).thenReturn(ACCOUNT);
    when(accountEntity.getBalance()).thenReturn(BALANCE);

    accountStateResponse = AccountStateResponse.of(accountEntity);

    assertEquals(AccountStatus.OPEN, accountStateResponse.accountStatus());
    assertEquals(BALANCE, accountStateResponse.balance());
    assertEquals(ACCOUNT, accountStateResponse.accountNumber());
    assertEquals(Currency.getInstance("EUR"), accountStateResponse.currency());
  }
}
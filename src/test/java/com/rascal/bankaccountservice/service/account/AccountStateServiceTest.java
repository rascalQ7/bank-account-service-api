package com.rascal.bankaccountservice.service.account;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.rascal.bankaccountservice.exception.NotFoundException;
import com.rascal.bankaccountservice.persistance.AccountRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AccountStateServiceTest {

  private static final String ACCOUNT = "account";
  @Mock
  private AccountRepository accountRepository;
  @InjectMocks
  private AccountStateService accountStateService;

  @Test
  void shouldThrowNotFoundExceptionWhenAccountDoesNotExist() {
    when(accountRepository.findByAccountNumber(ACCOUNT)).thenReturn(Optional.empty());
    assertThrows(NotFoundException.class, () -> accountStateService.getState(ACCOUNT));
  }
}
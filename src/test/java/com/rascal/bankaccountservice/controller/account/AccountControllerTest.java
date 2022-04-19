package com.rascal.bankaccountservice.controller.account;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.rascal.bankaccountservice.service.account.AccountStateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
class AccountControllerTest {


  private static final String ACCOUNT = "account";
  @Mock
  private AccountStateService accountStateService;
  private MockMvc mockMvc;
  @InjectMocks
  private AccountController accountController;

  @BeforeEach
  void setUp() {
    this.mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();
  }

  @Test
  void shouldGetAccountState() throws Exception {
    when(accountStateService.getState(ACCOUNT)).thenReturn(null);

    mockMvc.perform(get("/account/state/{accountNumber}", ACCOUNT))
        .andExpect(status().isOk());
  }
}
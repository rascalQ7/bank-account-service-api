package com.rascal.bankaccountservice.controller.transaction;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
class TransactionControllerTest {

  @Mock
  private TransactionRequest request;
  @Autowired
  private MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    when(request.getTransactionType()).thenReturn("DEBIT");
    when(request.getAmount()).thenReturn(0.01);
    when(request.getAccountNumber()).thenReturn("account");
    when(request.getCurrency()).thenReturn("EUR");
  }

  @Test
  void shouldReturnBadRequestWhenTransactionTypeIsInvalid() throws Exception {
    when(request.getTransactionType()).thenReturn("INVALID");

    mockMvc.perform(MockMvcRequestBuilders
        .post("/transaction/book")
        .contentType(MediaType.APPLICATION_JSON)
        .content(toJson(request)))
        .andExpect(status().isBadRequest())
        .andExpect(content()
            .string("{\"errorMessage\":\"transactionType: must be set to CREDIT or DEBIT\"}"));
  }

  @Test
  void shouldReturnBadRequestWhenAmountIsInvalid() throws Exception {
    when(request.getAmount()).thenReturn(-0.1);

    mockMvc.perform(MockMvcRequestBuilders
        .post("/transaction/book")
        .contentType(MediaType.APPLICATION_JSON)
        .content(toJson(request)))
        .andExpect(status().isBadRequest())
        .andExpect(content().string("{\"errorMessage\":\"amount: must follow ISO 4217\"}"));
  }

  @Test
  void shouldReturnBadRequestWhenAccountIsMissing() throws Exception {
    when(request.getAccountNumber()).thenReturn("");

    mockMvc.perform(MockMvcRequestBuilders
        .post("/transaction/book")
        .contentType(MediaType.APPLICATION_JSON)
        .content(toJson(request)))
        .andExpect(status().isBadRequest())
        .andExpect(content().string("{\"errorMessage\":\"accountNumber: must not be blank\"}"));
  }

  private String toJson(TransactionRequest request) {
    return "{" +
        " \"transactionType\": \"" + request.getTransactionType() + "\"," +
        " \"accountNumber\": \"" + request.getAccountNumber() + "\"," +
        " \"currency\": \"" + request.getCurrency() + "\"," +
        " \"amount\": " + request.getAmount() +
        " }";
  }
}
package com.rascal.bankaccountservice.controller.balance;

import com.rascal.bankaccountservice.domain.account.AccountState;
import com.rascal.bankaccountservice.service.account.AccountService;
import java.util.Objects;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/account", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountController {

  private final AccountService accountService;

  public AccountController(AccountService accountService) {
    this.accountService = Objects.requireNonNull(accountService);
  }


  @GetMapping("/state/{accountNumber}")
  @ResponseBody
  public AccountState getAccountStateByNumber(@PathVariable String accountNumber) {
    return accountService.getState(accountNumber);
  }
}

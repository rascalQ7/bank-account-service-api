package com.rascal.bankaccountservice.controller.account;

import com.rascal.bankaccountservice.service.account.AccountStateService;
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

  private final AccountStateService accountStateService;

  public AccountController(AccountStateService accountStateService) {
    this.accountStateService = Objects.requireNonNull(accountStateService);
  }

  /**
   * @param accountNumber - account number
   * @return AccountStateResponse - returns current account state with balance,
   * account currency and open/ close state
   */
  @GetMapping("/state/{accountNumber}")
  @ResponseBody
  public AccountStateResponse getAccountStateByAccountNumber(@PathVariable String accountNumber) {
    return accountStateService.getState(accountNumber);
  }
}

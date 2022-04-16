package com.rascal.bankaccountservice.controller.transaction;

import com.rascal.bankaccountservice.controller.transaction.validator.ValidatorUtils;
import com.rascal.bankaccountservice.domain.transaction.Transaction;
import com.rascal.bankaccountservice.service.transaction.TransactionServiceFactory;
import java.util.Objects;
import javax.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/transaction", produces = MediaType.APPLICATION_JSON_VALUE)
public class TransactionController {

  private final TransactionServiceFactory transactionServiceFactory;

  public TransactionController(TransactionServiceFactory transactionServiceFactory) {
    this.transactionServiceFactory = Objects.requireNonNull(transactionServiceFactory);
  }

  @PostMapping("/book")
  public ResponseEntity<Void> bookTransaction(
      @RequestBody @Valid TransactionRequest request,
      BindingResult bindingResult
  ) {
    ValidatorUtils.handleRequestValidationResult(bindingResult);
    var transaction = Transaction.of(request);
    var transactionService = transactionServiceFactory.getService(transaction.transactionType());
    transactionService.book(transaction);
    return ResponseEntity.ok().build();
  }
}

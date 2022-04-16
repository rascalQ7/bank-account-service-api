package com.rascal.bankaccountservice.controller.transaction;

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
      @RequestBody @Valid TransactionRequest transaction,
      BindingResult bindingResult
  ) {
    if (bindingResult.hasErrors()) {
      bindingResult.getFieldErrors().forEach(
          e -> System.out.println(e.getField() + ": " + e.getDefaultMessage())
      );
    }
//    System.out.println(transactionServiceFactory.getService(TransactionType.DEBIT).getClass());
//    System.out.println(transactionServiceFactory.getService(TransactionType.CREDIT).getClass());
//    System.out.println(transaction);
    return ResponseEntity.ok().build();
  }
}

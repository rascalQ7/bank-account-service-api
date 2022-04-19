package com.rascal.bankaccountservice.controller.transaction;

import com.rascal.bankaccountservice.controller.transaction.validator.RequestValidatorHandler;
import com.rascal.bankaccountservice.domain.transaction.Transaction;
import com.rascal.bankaccountservice.service.transaction.TransactionServiceFactory;
import java.util.Objects;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
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
  private final RequestValidatorHandler requestValidatorHandler;

  public TransactionController(
      TransactionServiceFactory transactionServiceFactory,
      RequestValidatorHandler requestValidatorHandler
  ) {
    this.transactionServiceFactory = Objects.requireNonNull(transactionServiceFactory);
    this.requestValidatorHandler = Objects.requireNonNull(requestValidatorHandler);
  }

  /**
   * Validates transaction request, selects booking service and books transaction
   *
   * @param request - transaction data
   * @param bindingResult - hibernates result of @request validation
   * @return created status if transaction is booked
   */
  @PostMapping("/book")
  public ResponseEntity<Void> bookTransaction(
      @RequestBody @Valid TransactionRequest request,
      BindingResult bindingResult
  ) {
    requestValidatorHandler.handle(bindingResult);
    var transaction = Transaction.of(request);
    var transactionService = transactionServiceFactory.getService(transaction.transactionType());
    transactionService.book(transaction);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }
}

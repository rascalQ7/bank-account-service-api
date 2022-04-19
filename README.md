# bank-account-service-api

### Usage
Start application: `gradle bootRun`


Account state request: 
``GET localhost:8080/account/state/1000123
``

Debit transaction:
``POST localhost:8080/transaction/book
{
"accountNumber": "1000123",
"amount": 11.21,
"currency": "EUR",
"transactionType": "DEBIT"
}
``


Credit transaction:
``POST localhost:8080/transaction/book
{
"accountNumber": "1000123",
"amount": 11.21,
"currency": "EUR",
"transactionType": "Credit"
}
``
##PAYLOAD & REQUESTS:
======================
1)POST REQUEST
    http://localhost:8082/api/accounts
    {
    "accountHolderName": "pradeep kumar kandyala",
    "balance": 180000
    }
2)GET REQUEST
    http://localhost:8082/api/accounts/1
3)PUT REQUEST
    http://localhost:8082/api/accounts/1/deposit
    send the below as a json request:
    {
    "amount": 15000
    }
4)PUT REQUEST
    http://localhost:8082/api/accounts/1/withdraw
    {
    "amount": 10000
    }
5)GET REQUEST (GET ALL ACCOUNTS)
    http://localhost:8082/api/accounts
6)DELETE ACCOUNT REST API
    http://localhost:8082/api/accounts/3

### Account Transfer Requests
1)POST:
=======
http://localhost:8082/api/accounts/transfer
    {
    "fromAccountId": 1,
    "toAccountId": 2,
    "amount": 22000
    }
### Transaction History
1)GET
======
http://localhost:8082/api/accounts/4/transactions
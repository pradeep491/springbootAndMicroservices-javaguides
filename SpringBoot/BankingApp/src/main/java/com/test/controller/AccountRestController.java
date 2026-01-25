package com.test.controller;

import com.test.dto.AccountDTO;
import com.test.dto.TransactionDTO;
import com.test.dto.TransferFundDTO;
import com.test.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountRestController {

    private final AccountService accountService;


    public AccountRestController(AccountService accountService) {
        this.accountService = accountService;
    }

    //Create Account REST API
    @PostMapping
    public ResponseEntity<AccountDTO> createAccount(@RequestBody AccountDTO accountDTO) {
        return new ResponseEntity<>(accountService.createAccount(accountDTO), HttpStatus.CREATED);
    }

    //GET ACCOUNT REST API
    @GetMapping("/{accountId}")
    public ResponseEntity<AccountDTO> getAccountById(@PathVariable Long accountId) {
        return new ResponseEntity<>(accountService.getAccountById(accountId), HttpStatus.OK);
    }

    //DEPOSIT REST API
    @PutMapping("/{accountId}/deposit")
    public ResponseEntity<AccountDTO> depositAmount(@PathVariable Long accountId,
                                                    @RequestBody Map<String, Double> request) {
        Double amount = request.get("amount");
        return new ResponseEntity<>(accountService.depositAmount(accountId, amount), HttpStatus.OK);
    }

    //Withdraw REST API
    @PutMapping("/{accountId}/withdraw")
    public ResponseEntity<AccountDTO> withdrawAmount(@PathVariable Long accountId,
                                                     @RequestBody Map<String, Double> request) {
        Double amount = request.get("amount");
        return new ResponseEntity<>(accountService.withdrawAmount(accountId, amount), HttpStatus.OK);
    }

    //Get All Accounts API
    @GetMapping
    public ResponseEntity<List<AccountDTO>> getAllAccounts() {
        return new ResponseEntity<>(accountService.getAllAcoounts(), HttpStatus.OK);
    }

    //DELETE ACCOUNT REST API
    @DeleteMapping("/{accountId}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long accountId) {
        return new ResponseEntity<>(accountService.deleteAccount(accountId), HttpStatus.OK);
    }

    //Build Transfer REST API
    @PostMapping("/transfer")
    public ResponseEntity<String> transferFund(@RequestBody TransferFundDTO transferFundDTO) {
        accountService.transferFunds(transferFundDTO);
        return new ResponseEntity<>("Transfer successful...!", HttpStatus.OK);
    }

    //Build Transactions REST API
    @GetMapping("/{accountId}/transactions")
    public ResponseEntity<List<TransactionDTO>> fetchAccountTransactions(@PathVariable Long accountId) {
        List<TransactionDTO> transactionDTOList = accountService.getAccountTransactions(accountId);
        return new ResponseEntity<>(transactionDTOList, HttpStatus.OK);
    }
}

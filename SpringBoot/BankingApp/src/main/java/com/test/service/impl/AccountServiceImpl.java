package com.test.service.impl;

import com.test.dto.AccountDTO;
import com.test.dto.TransactionDTO;
import com.test.dto.TransferFundDTO;
import com.test.entity.Account;
import com.test.entity.Transaction;
import com.test.exception.AccountException;
import com.test.repos.AccountRepository;
import com.test.repos.TransactionRepository;
import com.test.service.AccountService;
import com.test.utils.AccountMapper;
import com.test.utils.AppConstants;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    private final TransactionRepository transactionRepository;

    private final ModelMapper modelMapper;

    public AccountServiceImpl(AccountRepository accountRepository, TransactionRepository transactionRepository, ModelMapper modelMapper) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public AccountDTO createAccount(AccountDTO accountDTO) {
        //Account account = modelMapper.map(accountDTO, Account.class);
        Account account = AccountMapper.mapToEntity(accountDTO);
        Account savedAccount = accountRepository.save(account);
        //return modelMapper.map(savedAccount, AccountDTO.class);
        return AccountMapper.mapToDTO(savedAccount);
    }

    @Override
    public AccountDTO getAccountById(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountException("Account does not exist with account number:" + accountId));

        return AccountMapper.mapToDTO(account);
        //return modelMapper.map(account, AccountDTO.class);
    }

    @Override
    public AccountDTO depositAmount(Long id, double amount) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountException("Account does not exist with account number:" + id));
        account.setBalance(account.getBalance() + amount);
        Account savedAccount = accountRepository.save(account);

        //Logging Transaction
        Transaction transaction = new Transaction();
        transaction.setAccountId(id);
        transaction.setAmount(amount);
        transaction.setTimestamp(LocalDateTime.now());
        transaction.setTransactionType(AppConstants.DEPOSIT_TRANSACTION);

        transactionRepository.save(transaction);
        //return modelMapper.map(savedAccount, AccountDTO.class);
        return AccountMapper.mapToDTO(savedAccount);
    }

    @Override
    public AccountDTO withdrawAmount(Long id, double amount) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountException("Account does not exist with account number:" + id));
        if (account.getBalance() < amount) {
            throw new RuntimeException("insufficient balance...!");
        }
        account.setBalance(account.getBalance() - amount);
        Account savedAccount = accountRepository.save(account);

        //Logging Transaction
        Transaction transaction = new Transaction();
        transaction.setAccountId(id);
        transaction.setAmount(amount);
        transaction.setTimestamp(LocalDateTime.now());
        transaction.setTransactionType(AppConstants.WITHDRAW_TRANSACTION);

        transactionRepository.save(transaction);
        //return modelMapper.map(savedAccount, AccountDTO.class);
        return AccountMapper.mapToDTO(savedAccount);
    }

    @Override
    public List<AccountDTO> getAllAcoounts() {

        List<Account> accountList = accountRepository.findAll();
        /*return accountList.stream()
                .map(account -> modelMapper.map(account, AccountDTO.class)).toList();*/
        return accountList.stream()
                .map(account -> AccountMapper.mapToDTO(account)).toList();
    }

    @Override
    public String deleteAccount(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountException("Account does not exist with account number:" + accountId));
        accountRepository.delete(account);
        return "Account deleted successfully for account number:" + accountId;
    }

    @Override
    public void transferFunds(TransferFundDTO transferFundDTO) {
        Account fromAccount = accountRepository.findById(transferFundDTO.fromAccountId())
                .orElseThrow(() -> new AccountException("Account does not exist with account number:" + transferFundDTO.fromAccountId()));
        Account toAccount = accountRepository.findById(transferFundDTO.toAccountId())
                .orElseThrow(() -> new AccountException("Account does not exist with account number:" + transferFundDTO.toAccountId()));

        if (fromAccount.getBalance() < transferFundDTO.amount()) {
            throw new AccountException("there is no sufficient balance in the account:" + transferFundDTO.fromAccountId());
        }
        toAccount.setBalance(toAccount.getBalance() + transferFundDTO.amount());
        fromAccount.setBalance(fromAccount.getBalance() - transferFundDTO.amount());

        //Transfer Transaction
        Transaction transaction = new Transaction();
        transaction.setAccountId(transferFundDTO.fromAccountId());
        transaction.setAmount(transferFundDTO.amount());
        transaction.setTimestamp(LocalDateTime.now());
        transaction.setTransactionType(AppConstants.TRANSFER_TRANSACTION);
        transactionRepository.save(transaction);

        accountRepository.save(toAccount);
        accountRepository.save(fromAccount);
    }

    @Override
    public List<TransactionDTO> getAccountTransactions(Long accountId) {
        List<Transaction> transactionsList = transactionRepository
                .findByAccountIdOrderByTimestampDesc(accountId);

        return transactionsList.stream()
                .map(transaction -> mapToDTO(transaction))
                .toList();
    }

    private TransactionDTO mapToDTO(Transaction transaction) {

        return new TransactionDTO(transaction.getId(),
                transaction.getAccountId(),
                transaction.getAmount(),
                transaction.getTransactionType(),
                transaction.getTimestamp());
    }
}

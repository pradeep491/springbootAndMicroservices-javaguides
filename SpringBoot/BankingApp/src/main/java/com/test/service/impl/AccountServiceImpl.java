package com.test.service.impl;

import com.test.dto.AccountDTO;
import com.test.entity.Account;
import com.test.exception.AccountException;
import com.test.repos.AccountRepository;
import com.test.service.AccountService;
import com.test.utils.AccountMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    private final ModelMapper modelMapper;

    public AccountServiceImpl(AccountRepository accountRepository, ModelMapper modelMapper) {
        this.accountRepository = accountRepository;
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
}

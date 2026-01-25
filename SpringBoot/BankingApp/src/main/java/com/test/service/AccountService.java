package com.test.service;

import com.test.dto.AccountDTO;

import java.util.List;

public interface AccountService {
    public AccountDTO createAccount(AccountDTO accountDTO);

    public AccountDTO getAccountById(Long accountId);

    public AccountDTO depositAmount(Long id, double amount);

    public AccountDTO withdrawAmount(Long id,double amount);

    public List<AccountDTO> getAllAcoounts();

    public String deleteAccount(Long accountId);
}

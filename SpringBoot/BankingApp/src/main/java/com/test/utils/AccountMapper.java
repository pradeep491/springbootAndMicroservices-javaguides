package com.test.utils;

import com.test.dto.AccountDTO;
import com.test.entity.Account;

public class AccountMapper {
    public static Account mapToEntity(AccountDTO accountDTO) {
        Account account = new Account(accountDTO.id(),
                accountDTO.accountHolderName(),
                accountDTO.balance());
        return account;
    }

    public static AccountDTO mapToDTO(Account account) {
        AccountDTO accountDTO = new AccountDTO(account.getId(),
                account.getAccountHolderName(),
                account.getBalance());
        return accountDTO;
    }
}

package com.test.dto;

public record TransferFundDTO(Long fromAccountId,
                              Long toAccountId,
                              double amount) {
}

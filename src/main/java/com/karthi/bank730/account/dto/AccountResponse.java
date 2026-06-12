package com.karthi.bank730.account.dto;

import com.karthi.bank730.account.enums.AccountType;

import java.math.BigDecimal;

public record AccountResponse(
        String accountNumber,
        String customerName,
        AccountType accountType,
        BigDecimal balance
) {
}

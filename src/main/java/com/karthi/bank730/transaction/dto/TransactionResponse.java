package com.karthi.bank730.transaction.dto;

import com.karthi.bank730.transaction.enmus.TransactionType;

import java.math.BigDecimal;

public record TransactionResponse(
        String reference,
        TransactionType type,
        BigDecimal amount,
        BigDecimal balanceAfter
) {
}

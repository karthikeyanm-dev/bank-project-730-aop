package com.karthi.bank730.transaction.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record TransferRequest(

        @NotBlank
        String fromAccountNumber,

        @NotBlank
        String toAccountNumber,

        @Positive
        BigDecimal amount,

        String remarks
) {
}

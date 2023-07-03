package br.com.doardin.myexpenses.application.transaction.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record PostTransactionDto(
        @NotBlank String description,
        @Min(1) BigDecimal amount,
        @NotBlank String categoryId) {
}

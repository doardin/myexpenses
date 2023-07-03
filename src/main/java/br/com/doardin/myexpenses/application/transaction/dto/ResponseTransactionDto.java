package br.com.doardin.myexpenses.application.transaction.dto;

import java.math.BigDecimal;

import br.com.doardin.myexpenses.application.category.dto.ResponseCategoryDto;
import lombok.Builder;

@Builder
public record ResponseTransactionDto(
        String id,
        String description,
        BigDecimal amount,
        ResponseCategoryDto category) {
}

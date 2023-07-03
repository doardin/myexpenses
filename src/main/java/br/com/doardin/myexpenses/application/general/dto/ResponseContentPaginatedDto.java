package br.com.doardin.myexpenses.application.general.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.Builder;

@Builder
public record ResponseContentPaginatedDto(
        int totalPages,
        BigDecimal entries,
        BigDecimal withdrawals,
        List<?> content) {
}

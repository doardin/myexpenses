package br.com.doardin.myexpenses.application.category.dto;

import java.time.LocalDateTime;

import br.com.doardin.myexpenses.enums.CategoryTypes;
import lombok.Builder;

@Builder
public record ResponseCategoryDto(
        String id,
        String name,
        CategoryTypes type,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {
}

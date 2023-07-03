package br.com.doardin.myexpenses.mapper;

import java.util.UUID;

import org.springframework.stereotype.Component;

import br.com.doardin.myexpenses.application.category.dto.PostCategoryDto;
import br.com.doardin.myexpenses.application.category.dto.ResponseCategoryDto;
import br.com.doardin.myexpenses.domain.category.Category;
import br.com.doardin.myexpenses.domain.user.User;
import br.com.doardin.myexpenses.enums.CategoryTypes;

@Component
public class CategoryMapper {

    public Category toCategory(PostCategoryDto dto, User user) {
        return Category.builder()
                .id(UUID.randomUUID().toString())
                .name(dto.name())
                .type(CategoryTypes.valueOf(dto.type()))
                .user(user)
                .build();
    }

    public ResponseCategoryDto toResponseCategoryDto(Category category) {
        return ResponseCategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .type(category.getType())
                .createdAt(category.getCreatedAt())
                .updatedAt(category.getUpdatedAt())
                .build();
    }

}

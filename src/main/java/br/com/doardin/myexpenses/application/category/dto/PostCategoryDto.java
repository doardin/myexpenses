package br.com.doardin.myexpenses.application.category.dto;

import br.com.doardin.myexpenses.annotations.validenum.ValidEnum;
import br.com.doardin.myexpenses.enums.CategoryTypes;
import jakarta.validation.constraints.NotBlank;

public record PostCategoryDto(
        @NotBlank String name,
        @ValidEnum(enumClass = CategoryTypes.class) String type) {
}

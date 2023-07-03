package br.com.doardin.myexpenses.application.category.dto;

import br.com.doardin.myexpenses.annotations.validenum.ValidEnum;
import br.com.doardin.myexpenses.enums.CategoryTypes;

public record PostCategoryDto(
        String name,
        @ValidEnum(enumClass = CategoryTypes.class) String type) {
}

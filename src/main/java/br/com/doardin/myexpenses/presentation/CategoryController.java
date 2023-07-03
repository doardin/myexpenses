package br.com.doardin.myexpenses.presentation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.doardin.myexpenses.application.category.dto.PostCategoryDto;
import br.com.doardin.myexpenses.application.category.dto.ResponseCategoryDto;
import br.com.doardin.myexpenses.application.category.service.CategoryAppService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryAppService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseCategoryDto createCategory(@RequestBody @Valid PostCategoryDto dto) {
        return service.createCategory(dto);
    }

}

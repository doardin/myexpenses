package br.com.doardin.myexpenses.presentation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.doardin.myexpenses.application.category.dto.PostCategoryDto;
import br.com.doardin.myexpenses.application.category.dto.ResponseCategoryDto;
import br.com.doardin.myexpenses.application.category.service.CategoryAppService;
import br.com.doardin.myexpenses.application.general.dto.ResponseContentPaginatedDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryAppService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseCategoryDto createCategory(@RequestBody @Valid PostCategoryDto dto) {
        return service.createCategory(dto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseContentPaginatedDto getCategories(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return service.getCategories(page, size);
    }

}

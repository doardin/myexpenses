package br.com.doardin.myexpenses.application.category.service;

import org.springframework.stereotype.Service;

import br.com.doardin.myexpenses.application.category.dto.PostCategoryDto;
import br.com.doardin.myexpenses.application.category.dto.ResponseCategoryDto;
import br.com.doardin.myexpenses.domain.category.CategoryRepository;
import br.com.doardin.myexpenses.mapper.CategoryMapper;
import br.com.doardin.myexpenses.util.CurrentUserUtil;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryAppService {

    private final CategoryMapper mapper;
    private final CurrentUserUtil currentUserUtil;
    private final CategoryRepository categoryRepository;

    public ResponseCategoryDto createCategory(PostCategoryDto dto) {
        var user = currentUserUtil.getUserFromSecurityContextHolder();
        var category = mapper.toCategory(dto, user);
        category = categoryRepository.save(category);
        return mapper.toResponseCategoryDto(category);
    }

}

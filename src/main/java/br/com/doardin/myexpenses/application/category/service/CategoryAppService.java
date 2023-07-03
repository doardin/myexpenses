package br.com.doardin.myexpenses.application.category.service;

import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.doardin.myexpenses.application.category.dto.PostCategoryDto;
import br.com.doardin.myexpenses.application.category.dto.ResponseCategoryDto;
import br.com.doardin.myexpenses.application.general.dto.ResponseContentPaginatedDto;
import br.com.doardin.myexpenses.domain.category.CategoryRepository;
import br.com.doardin.myexpenses.mapper.CategoryMapper;
import br.com.doardin.myexpenses.util.CurrentUserUtil;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryAppService {

    private final CategoryMapper mapper;
    private final CategoryRepository repository;
    private final CurrentUserUtil currentUserUtil;

    public ResponseCategoryDto createCategory(PostCategoryDto dto) {
        var user = currentUserUtil.getUserFromSecurityContextHolder();
        var category = mapper.toCategory(dto, user);
        category = repository.save(category);
        return mapper.toResponseCategoryDto(category);
    }

    public ResponseContentPaginatedDto getCategories(int page, int size) {
        var pageable = PageRequest.of(page, size, Direction.DESC, "createdAt");
        var user = currentUserUtil.getUserFromSecurityContextHolder();
        var categories = repository.findAllByUser(user, pageable);

        return ResponseContentPaginatedDto.builder()
                .totalPages(categories.getTotalPages())
                .content(categories.getContent().stream().map(mapper::toResponseCategoryDto)
                        .collect(Collectors.toList()))
                .build();
    }

}

package br.com.doardin.myexpenses.application.transaction.service;

import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.doardin.myexpenses.application.general.dto.ResponseContentPaginatedDto;
import br.com.doardin.myexpenses.application.transaction.dto.PostTransactionDto;
import br.com.doardin.myexpenses.application.transaction.dto.ResponseTransactionDto;
import br.com.doardin.myexpenses.domain.category.CategoryRepository;
import br.com.doardin.myexpenses.domain.transaction.TransactionRepository;
import br.com.doardin.myexpenses.enums.CategoryTypes;
import br.com.doardin.myexpenses.exceptions.ApiCustomException;
import br.com.doardin.myexpenses.mapper.TransactionMapper;
import br.com.doardin.myexpenses.util.CurrentUserUtil;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionAppService {

    private final TransactionMapper mapper;
    private final CurrentUserUtil currentUserUtil;
    private final TransactionRepository repository;
    private final CategoryRepository categoryRepository;

    public ResponseTransactionDto createTransaction(PostTransactionDto dto) {
        var user = currentUserUtil.getUserFromSecurityContextHolder();
        var category = categoryRepository.findById(dto.categoryId()).orElseThrow(
                () -> ApiCustomException.builder()
                        .message("Category not found")
                        .responseStatus(HttpStatus.NOT_FOUND)
                        .build());
        var transaction = this.mapper.toTransaction(dto, user, category);
        transaction = repository.save(transaction);
        return mapper.toResponseTransactionDto(transaction, category);
    }

    public ResponseContentPaginatedDto getTransactions(int page, int size) {
        var pageable = PageRequest.of(page, size, Direction.DESC, "createdAt");
        var user = currentUserUtil.getUserFromSecurityContextHolder();
        var transactions = this.repository.findAllByUser(user, pageable);

        var entries = this.repository.sumTransactionsByCategoryType(user, CategoryTypes.ENTRY);
        var withdrawals = this.repository.sumTransactionsByCategoryType(user, CategoryTypes.WITHDRAW);

        return ResponseContentPaginatedDto.builder()
                .totalPages(transactions.getTotalPages())
                .entries(entries)
                .withdrawals(withdrawals)
                .content(transactions.getContent().stream().map(mapper::toResponseTransactionDto)
                        .collect(Collectors.toList()))
                .build();
    }

}

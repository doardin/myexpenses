package br.com.doardin.myexpenses.application.transaction.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.doardin.myexpenses.application.transaction.dto.PostTransactionDto;
import br.com.doardin.myexpenses.application.transaction.dto.ResponseTransactionDto;
import br.com.doardin.myexpenses.domain.category.CategoryRepository;
import br.com.doardin.myexpenses.domain.transaction.TransactionRepository;
import br.com.doardin.myexpenses.exceptions.ApiCustomException;
import br.com.doardin.myexpenses.mapper.TransactionMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionAppService {

    private final TransactionMapper mapper;
    private final TransactionRepository repository;
    private final CategoryRepository categoryRepository;

    public ResponseTransactionDto createTransaction(PostTransactionDto dto) {
        var category = categoryRepository.findById(dto.categoryId()).orElseThrow(
                () -> ApiCustomException.builder()
                        .message("Category not found")
                        .responseStatus(HttpStatus.NOT_FOUND)
                        .build());
        var transaction = this.mapper.toTransaction(dto, category);
        transaction = repository.save(transaction);
        return mapper.toResponseTransactionDto(transaction, category);
    }

}

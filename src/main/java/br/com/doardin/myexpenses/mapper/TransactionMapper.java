package br.com.doardin.myexpenses.mapper;

import java.util.UUID;

import org.springframework.stereotype.Component;

import br.com.doardin.myexpenses.application.transaction.dto.PostTransactionDto;
import br.com.doardin.myexpenses.application.transaction.dto.ResponseTransactionDto;
import br.com.doardin.myexpenses.domain.category.Category;
import br.com.doardin.myexpenses.domain.transaction.Transaction;
import br.com.doardin.myexpenses.domain.user.User;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TransactionMapper {

    private final CategoryMapper categoryMapper;

    public Transaction toTransaction(PostTransactionDto dto, User user, Category category) {
        return Transaction.builder()
                .id(UUID.randomUUID().toString())
                .description(dto.description())
                .amount(dto.amount())
                .category(category)
                .user(user)
                .build();
    }

    public ResponseTransactionDto toResponseTransactionDto(Transaction transaction, Category category) {
        var categoryDto = this.categoryMapper.toResponseCategoryDto(category);
        return ResponseTransactionDto.builder()
                .id(transaction.getId())
                .description(transaction.getDescription())
                .amount(transaction.getAmount())
                .category(categoryDto)
                .build();
    }

}

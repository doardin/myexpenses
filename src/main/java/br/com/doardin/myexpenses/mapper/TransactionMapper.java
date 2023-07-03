package br.com.doardin.myexpenses.mapper;

import java.util.UUID;

import org.springframework.stereotype.Component;

import br.com.doardin.myexpenses.application.transaction.dto.PostTransactionDto;
import br.com.doardin.myexpenses.application.transaction.dto.ResponseTransactionDto;
import br.com.doardin.myexpenses.domain.category.Category;
import br.com.doardin.myexpenses.domain.paymentmethod.PaymentMethod;
import br.com.doardin.myexpenses.domain.transaction.Transaction;
import br.com.doardin.myexpenses.domain.user.User;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TransactionMapper {

    private final CategoryMapper categoryMapper;
    private final PaymentMethodMapper paymentMethodMapper;

    public Transaction toTransaction(PostTransactionDto dto, User user, Category category,
            PaymentMethod paymentMethod) {
        return Transaction.builder()
                .id(UUID.randomUUID().toString())
                .description(dto.description())
                .amount(dto.amount())
                .category(category)
                .paymentMethod(paymentMethod)
                .user(user)
                .build();
    }

    public ResponseTransactionDto toResponseTransactionDto(Transaction transaction, Category category,
            PaymentMethod paymentMethod) {
        var categoryDto = this.categoryMapper.toResponseCategoryDto(category);
        var paymentMethodDto = this.paymentMethodMapper.toResponsePaymentMethod(paymentMethod);

        return ResponseTransactionDto.builder()
                .id(transaction.getId())
                .description(transaction.getDescription())
                .amount(transaction.getAmount())
                .category(categoryDto)
                .paymentMethod(paymentMethodDto)
                .build();
    }

    public ResponseTransactionDto toResponseTransactionDto(Transaction transaction) {
        var category = this.categoryMapper.toResponseCategoryDto(transaction.getCategory());
        var paymentMethod = this.paymentMethodMapper.toResponsePaymentMethod(transaction.getPaymentMethod());

        return ResponseTransactionDto.builder()
                .id(transaction.getId())
                .description(transaction.getDescription())
                .amount(transaction.getAmount())
                .category(category)
                .paymentMethod(paymentMethod)
                .build();
    }

}

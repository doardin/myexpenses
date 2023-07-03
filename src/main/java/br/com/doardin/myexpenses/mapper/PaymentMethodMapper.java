package br.com.doardin.myexpenses.mapper;

import java.util.UUID;

import org.springframework.stereotype.Component;

import br.com.doardin.myexpenses.application.paymentmethod.dto.PostPaymentMethod;
import br.com.doardin.myexpenses.application.paymentmethod.dto.ResponsePaymentMethodDto;
import br.com.doardin.myexpenses.domain.paymentmethod.PaymentMethod;
import br.com.doardin.myexpenses.domain.user.User;

@Component
public class PaymentMethodMapper {

    public PaymentMethod toPaymentMethod(PostPaymentMethod dto, User user) {
        return PaymentMethod.builder()
                .id(UUID.randomUUID().toString())
                .name(dto.name())
                .user(user)
                .build();
    }

    public ResponsePaymentMethodDto toResponsePaymentMethod(PaymentMethod paymentMethod) {
        return ResponsePaymentMethodDto.builder()
                .id(paymentMethod.getId())
                .name(paymentMethod.getName())
                .createdAt(paymentMethod.getCreatedAt())
                .updatedAt(paymentMethod.getUpdatedAt())
                .build();
    }

}

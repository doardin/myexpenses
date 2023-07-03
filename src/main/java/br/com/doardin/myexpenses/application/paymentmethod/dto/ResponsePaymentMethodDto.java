package br.com.doardin.myexpenses.application.paymentmethod.dto;

import java.time.LocalDateTime;

import lombok.Builder;

@Builder
public record ResponsePaymentMethodDto(
        String id,
        String name,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

}

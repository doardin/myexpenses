package br.com.doardin.myexpenses.application.paymentmethod.dto;

import jakarta.validation.constraints.NotBlank;

public record PostPaymentMethod(
        @NotBlank String name) {
}

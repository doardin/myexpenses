package br.com.doardin.myexpenses.application.user.dto;

import jakarta.validation.constraints.NotBlank;

public record PostUserSignInDto(
        @NotBlank String email,
        @NotBlank String password) {

}

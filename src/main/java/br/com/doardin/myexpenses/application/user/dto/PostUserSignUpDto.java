package br.com.doardin.myexpenses.application.user.dto;

import br.com.doardin.myexpenses.annotations.fieldmatch.MatchFields;
import br.com.doardin.myexpenses.annotations.uniqueemail.UniqueEmailSubset;
import jakarta.validation.constraints.NotBlank;

@MatchFields(fields = { "password", "passwordConfirmation" })
public record PostUserSignUpDto(
        @NotBlank String name,
        @NotBlank @UniqueEmailSubset String email,
        @NotBlank String password,
        @NotBlank String passwordConfirmation) {
}

package br.com.doardin.myexpenses.application.user.dto;

import br.com.doardin.myexpenses.annotations.fieldmatch.MatchFields;
import br.com.doardin.myexpenses.annotations.uniqueemail.UniqueEmailSubset;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
@MatchFields(fields = { "password", "passwordConfirmation" })
public class PostUserSignUpDto {
    @NotBlank
    private String name;

    @NotBlank
    @UniqueEmailSubset
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String passwordConfirmation;
}

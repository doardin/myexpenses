package br.com.doardin.myexpenses.application.user.dto;

import lombok.Builder;

@Builder
public record ResponseUserSignInDto(
        String token) {

}

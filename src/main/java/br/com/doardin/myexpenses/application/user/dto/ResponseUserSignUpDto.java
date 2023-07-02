package br.com.doardin.myexpenses.application.user.dto;

import java.time.LocalDateTime;

import lombok.Builder;

@Builder
public record ResponseUserSignUpDto(
        String id,
        String name,
        String email,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {
}

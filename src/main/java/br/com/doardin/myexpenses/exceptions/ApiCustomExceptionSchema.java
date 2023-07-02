package br.com.doardin.myexpenses.exceptions;

import java.time.LocalDateTime;

import lombok.Builder;

@Builder
public record ApiCustomExceptionSchema(String message, int status,
        LocalDateTime timestamp) {
}

package br.com.doardin.myexpenses.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ApiCustomException extends RuntimeException {
    private final String message;
    @Builder.Default
    private final HttpStatus responseStatus = HttpStatus.BAD_REQUEST;
}

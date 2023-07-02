package br.com.doardin.myexpenses.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiCustomExceptionSchema> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception) {

        var message = exception.getAllErrors().get(0).getDefaultMessage();
        if (exception.getFieldErrorCount() > 0) {
            var fieldError = exception.getFieldErrors().get(0);
            message = String.format("%s: %s", fieldError.getField(), fieldError.getDefaultMessage());
        }

        var response = ApiCustomExceptionSchema.builder()
                .message(message)
                .status(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(ApiCustomException.class)
    public ResponseEntity<ApiCustomExceptionSchema> handleApiCustomException(ApiCustomException exception) {
        var response = ApiCustomExceptionSchema.builder()
                .message(exception.getMessage())
                .status(exception.getResponseStatus().value())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(exception.getResponseStatus()).body(response);
    }

}

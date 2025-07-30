package com.example.spring_basics.handlers;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@RestControllerAdvice
public class SimpleExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleUniqueViolation(DataIntegrityViolationException exception) {
        String errorMessage = "Valor duplicado não permitido";

        Throwable rootCause = exception.getRootCause();
        if (rootCause != null) {
            String message = rootCause.getMessage();
            if (message.contains("name")) {
                errorMessage = "Nome já cadastrado";
            }
            if (message.contains("iso_code")) {
                errorMessage = "Código ISO já cadastrado";
            }
        }

        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessage);
    }
}

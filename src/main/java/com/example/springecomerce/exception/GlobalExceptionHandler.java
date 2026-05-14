package com.example.springecomerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(ResourceNotFoundException ex){
        Map<String , String> error = new HashMap<>();
        error.put("error" , ex.getMessage());
        return new ResponseEntity<>(error , HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException ex){
        Map<String , String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(err -> errors.put(err.getField(), err.getDefaultMessage()));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(org.springframework.dao.DataIntegrityViolationException.class)
    public ResponseEntity<?> handleDataIntegrityViolationException(org.springframework.dao.DataIntegrityViolationException ex){
        Map<String , String> error = new HashMap<>();
        String message = ex.getRootCause() != null ? ex.getRootCause().getMessage() : ex.getMessage();
        
        if (message.contains("duplicate key value violates unique constraint") || message.contains("already exists")) {
            error.put("error", "Email already exists. Please use a different email.");
        } else {
            error.put("error", "Data integrity violation.");
        }
        return new ResponseEntity<>(error , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGenericException(Exception ex){
        Map<String , String> error = new HashMap<>();
        error.put("error" , ex.getMessage());
        return new ResponseEntity<>(error , HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

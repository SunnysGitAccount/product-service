package com.dev.sunny.controllers;

import com.dev.sunny.exceptions.NoProductFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class CustomErrorHandler {
    @ExceptionHandler
    public ResponseEntity<Map<String, String>> handleProductNotFoundException(NoProductFoundException exception) {
        Map<String, String> errorMap = Map.of("message", exception.getMessage());
        return new ResponseEntity<>(errorMap, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<Map<String, String>> handleException(RuntimeException exception) {
        Map<String, String> errorMap = Map.of("message", exception.getMessage());
        return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
    }
}

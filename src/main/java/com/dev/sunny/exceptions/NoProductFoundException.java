package com.dev.sunny.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No product found with given ID!")
public class NoProductFoundException extends Throwable {
    public NoProductFoundException(String message) {
        super(message);
    }
}

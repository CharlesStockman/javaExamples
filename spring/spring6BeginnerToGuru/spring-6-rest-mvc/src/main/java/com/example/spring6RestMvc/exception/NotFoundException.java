package com.example.spring6RestMvc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason="The primary key was found in the data structure or database")
public class NotFoundException extends RuntimeException {

    public NotFoundException() {
        super();
    }
}

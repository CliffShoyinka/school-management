package com.schoolmanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT) //custom excetionda firlamasini istedigimiz exception'i sectik
public class ConflictException extends RuntimeException{

    public ConflictException(String message) {
        super(message);
    }
}

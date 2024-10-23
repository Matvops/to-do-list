package com.cadenassi.to_do_list.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ObjectIsNullException extends RuntimeException{

    public ObjectIsNullException() {
    }

    public ObjectIsNullException(String message) {
        super(message);
    }
}

package com.cadenassi.to_do_list.exceptions;

import java.io.Serializable;
import java.time.Instant;

public class ExceptionResponse implements Serializable {

    private Instant timestamp;
    private String message;
    private String details;

    public ExceptionResponse() {
    }

    public ExceptionResponse(Instant timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}

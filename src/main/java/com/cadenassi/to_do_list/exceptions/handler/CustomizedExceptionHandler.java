package com.cadenassi.to_do_list.exceptions.handler;

import com.cadenassi.to_do_list.exceptions.ExceptionResponse;
import com.cadenassi.to_do_list.exceptions.ObjectIsNullException;
import com.cadenassi.to_do_list.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;

/**
 * @author Matheus
 */

@ControllerAdvice
public class CustomizedExceptionHandler{


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> exceptionHandler(Exception e, WebRequest webRequest){

        ExceptionResponse response = new ExceptionResponse(Instant.now(), e.getMessage(), webRequest
                .getDescription(false));

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> resourceNotFoundException(ResourceNotFoundException e, WebRequest webRequest){

        ExceptionResponse response = new ExceptionResponse(Instant.now(), e.getMessage(), webRequest
                .getDescription(false));

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(ObjectIsNullException.class)
    public ResponseEntity<ExceptionResponse> ObjectIsNullException(ObjectIsNullException e, WebRequest webRequest){

        ExceptionResponse response = new ExceptionResponse(Instant.now(), e.getMessage(), webRequest
                .getDescription(false));

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

}

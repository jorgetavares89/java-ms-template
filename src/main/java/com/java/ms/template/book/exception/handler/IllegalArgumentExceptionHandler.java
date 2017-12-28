package com.java.ms.template.book.exception.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.java.ms.template.book.exception.CustomExceptionHandler;

public class IllegalArgumentExceptionHandler extends CustomExceptionHandler {

    @ExceptionHandler(value = {IllegalArgumentException.class})
    protected ResponseEntity<Object> handleBadRequest(IllegalArgumentException ex, WebRequest request) {
        return handleExceptionInternal(ex,
                getErrorResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR),
                new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                request);
    }
}

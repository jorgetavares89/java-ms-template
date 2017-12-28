package com.java.ms.template.book.exception.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.java.ms.template.book.exception.CustomExceptionHandler;
import com.java.ms.template.book.exception.NotFoundException;

@ControllerAdvice
public class NotFoundExceptionHandler extends CustomExceptionHandler {

    @ExceptionHandler(value = {NotFoundException.class})
    protected ResponseEntity<Object> handleNotFound(NotFoundException ex, WebRequest request) {
        return handleExceptionInternal(ex,
                getErrorResponse(ex, HttpStatus.NOT_FOUND),
                new HttpHeaders(),
                HttpStatus.NOT_FOUND,
                request);
    }
}

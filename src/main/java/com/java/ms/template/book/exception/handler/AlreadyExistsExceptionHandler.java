package com.java.ms.template.book.exception.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.java.ms.template.book.exception.AlreadyExistsException;
import com.java.ms.template.book.exception.CustomExceptionHandler;

@ControllerAdvice
public class AlreadyExistsExceptionHandler extends CustomExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(AlreadyExistsExceptionHandler.class);

    @ExceptionHandler(value = {AlreadyExistsException.class})
    protected ResponseEntity<Object> handleConflict(AlreadyExistsException ex, WebRequest request) {
        LOG.debug("AlreadyExistsExceptionHandler.handleConflict: handling exception");
        return handleExceptionInternal(ex,
                getErrorResponse(ex, HttpStatus.CONFLICT),
                new HttpHeaders(),
                HttpStatus.CONFLICT,
                request);
    }
}

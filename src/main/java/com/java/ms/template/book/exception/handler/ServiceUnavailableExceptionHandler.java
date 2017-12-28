package com.java.ms.template.book.exception.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.java.ms.template.book.exception.CustomExceptionHandler;
import com.java.ms.template.book.exception.ServiceUnavailableException;

@ControllerAdvice
public class ServiceUnavailableExceptionHandler extends CustomExceptionHandler{
	
	@ExceptionHandler(value = {ServiceUnavailableException.class})
    protected ResponseEntity<Object> handleServiceUnavailable(ServiceUnavailableException ex, WebRequest request) {
        return handleExceptionInternal(ex,
                getErrorResponse(ex, HttpStatus.SERVICE_UNAVAILABLE),
                new HttpHeaders(),
                HttpStatus.SERVICE_UNAVAILABLE,
                request);
    }
}

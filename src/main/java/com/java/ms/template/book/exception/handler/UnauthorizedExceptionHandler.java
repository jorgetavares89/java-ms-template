package com.java.ms.template.book.exception.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.java.ms.template.book.exception.CustomExceptionHandler;
import com.java.ms.template.book.exception.UnauthorizedException;

@ControllerAdvice
public class UnauthorizedExceptionHandler extends CustomExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(UnauthorizedExceptionHandler.class);

    @ExceptionHandler(value = {UnauthorizedException.class, JWTDecodeException.class})
    protected ResponseEntity<Object> handleUnauthorized(UnauthorizedException ex, WebRequest request) {
        LOG.debug("UnauthorizedExceptionHandler.handleUnauthorized: handling exception");
        return handleExceptionInternal(ex,
                getErrorResponse(ex, HttpStatus.UNAUTHORIZED),
                new HttpHeaders(),
                HttpStatus.UNAUTHORIZED,
                request);
    }
}

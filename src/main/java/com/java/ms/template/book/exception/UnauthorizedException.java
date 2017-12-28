package com.java.ms.template.book.exception;

public class UnauthorizedException extends RuntimeException {

    private static final long serialVersionUID = 1320945787231082617L;

    public UnauthorizedException() {
    }

    public UnauthorizedException(String message) {
        super(message);
    }
}

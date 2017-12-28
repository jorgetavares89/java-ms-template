package com.java.ms.template.book.exception;

public class BadRequestException extends RuntimeException {

    private static final long serialVersionUID = -8061816074536485960L;

    public BadRequestException() {
        super();
    }

    public BadRequestException(String message) {
        super(message);
    }
}

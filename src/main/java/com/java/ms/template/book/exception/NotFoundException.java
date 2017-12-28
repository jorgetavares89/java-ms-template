package com.java.ms.template.book.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }

    private static final long serialVersionUID = -4233753300667670068L;
}

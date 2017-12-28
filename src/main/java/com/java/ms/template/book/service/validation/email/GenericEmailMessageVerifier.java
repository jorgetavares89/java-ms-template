package com.java.ms.template.book.service.validation.email;

public abstract class GenericEmailMessageVerifier implements EmailMessageVerifier {

    protected abstract boolean execute(String message);

    @Override
    public boolean validate(String message) {
        return execute(message);
    }
}

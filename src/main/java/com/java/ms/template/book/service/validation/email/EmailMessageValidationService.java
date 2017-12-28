package com.java.ms.template.book.service.validation.email;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmailMessageValidationService {

    private List<EmailMessageVerifier> verifiers;

    @Autowired
    public EmailMessageValidationService(List<EmailMessageVerifier> verifiers) {
        this.verifiers = verifiers;
    }

    public boolean validate(String message) {
        return verifiers.stream()
                .allMatch(verifier -> verifier.validate(message) == true);
    }
}
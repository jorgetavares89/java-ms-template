package com.java.ms.template.book.service.validation.email;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.java.ms.template.book.exception.BadRequestException;

public class EmailMessageValidator implements ConstraintValidator<EmailMessageValidation, String> {

    private EmailMessageValidationService service;

    @Autowired
    public EmailMessageValidator(EmailMessageValidationService service) {
        this.service = service;
    }

    @Override
    public void initialize(EmailMessageValidation constraintAnnotation) {
    }

    @Override
    public boolean isValid(String message, ConstraintValidatorContext context) {
        if (!service.validate(message)) {
            throw new BadRequestException("The email message must contains at least the tag <qrcode>");
        }
        return true;
    }
}
package com.java.ms.template.book.service.validation.email;

import org.springframework.stereotype.Component;

@Component
public class EmailMessageQrCodeTagValidator extends GenericEmailMessageVerifier {

    @Override
    protected boolean execute(String message) {
        return message.contains("<qr-code>");
    }
}

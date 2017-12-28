package com.java.ms.template.book.domain;

import org.springframework.http.HttpStatus;

public class ErrorResponse {

    private HttpStatus status;
    private String message;
    private String error;
    private int code;

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static class Builder {

        private ErrorResponse errorResponse = new ErrorResponse();

        public Builder withStatus(HttpStatus status) {
            this.errorResponse.setStatus(status);
            return this;
        }

        public Builder withMessage(String message) {
            this.errorResponse.setMessage(message);
            return this;
        }

        public Builder withError(String error) {
            this.errorResponse.setError(error);
            return this;
        }

        public Builder withCode(int code) {
            this.errorResponse.setCode(code);
            return this;
        }

        public ErrorResponse build() {
            return this.errorResponse;
        }
    }
}
package com.bereda.exception;

import org.springframework.http.HttpStatus;

public class ErrorDetails {
    private String message;
    private String details;
    private HttpStatus status;

    public ErrorDetails(String message, String details, HttpStatus status) {
        this.message = message;
        this.details = details;
        this.status = status;
    }

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

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}

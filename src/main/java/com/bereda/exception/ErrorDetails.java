package com.bereda.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ErrorDetails {
    private LocalDateTime datestamp;
    private String message;
    private String details;
    private HttpStatus status;

    public ErrorDetails(LocalDateTime datestamp, String message, String details, HttpStatus status) {
        this.datestamp = datestamp;
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

    public LocalDateTime getDatestamp() {
        return datestamp;
    }

    public void setDatestamp(LocalDateTime datestamp) {
        this.datestamp = datestamp;
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

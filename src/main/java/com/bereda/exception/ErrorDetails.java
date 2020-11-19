package com.bereda.exception;

import lombok.Builder;
import lombok.Value;
import org.springframework.http.HttpStatus;

@Builder
@Value
public class ErrorDetails {
    String message;
    String details;
    HttpStatus status;
}

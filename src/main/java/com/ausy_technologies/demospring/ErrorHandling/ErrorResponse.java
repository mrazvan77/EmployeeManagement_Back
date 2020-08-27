package com.ausy_technologies.demospring.ErrorHandling;

import org.springframework.http.HttpStatus;

public class ErrorResponse extends RuntimeException{

    private HttpStatus status;
    private String message;

    public ErrorResponse(String message) {
        super(message);
        this.message = message;
    }

    public ErrorResponse(HttpStatus status, String message) {
        super(message);
        this.status = status;
        this.message = message;
    }

    public ErrorResponse(String message, Throwable errors) {
        super(message, errors);
        this.status = status;
        this.message = message;
    }
}

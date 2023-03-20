package com.devsu.api.application.exceptions.responses;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
public class ErrorResponse {
    private String exception;

    private String message;

    private List<ErrorDetails> errors;

    private String path;

    public ErrorResponse(Exception exception, String path, List<ErrorDetails> errors) {
        this.exception = exception.getClass().getSimpleName();
        this.message = exception.getMessage();
        this.path = path;
        this.errors = errors;
    }


    @Data
    public static class ErrorDetails {
        private String fieldName;
        private String message;
    }
}

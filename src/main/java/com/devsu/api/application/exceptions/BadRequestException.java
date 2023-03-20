package com.devsu.api.application.exceptions;

public class BadRequestException extends RuntimeException {
    private static final String DESCRIPTION = "Bad Request Exception (400)";

    public BadRequestException(String detail) {
        super(detail.length() > 0 ? detail : DESCRIPTION );
    }
}
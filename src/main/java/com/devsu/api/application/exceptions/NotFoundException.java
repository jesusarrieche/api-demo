package com.devsu.api.application.exceptions;

public class NotFoundException extends RuntimeException {

    private static final String DESCRIPTION = "Not Found Exception (404)";

    public NotFoundException(String detail) {
        super(detail.length() > 0 ? detail : DESCRIPTION);
    }
}

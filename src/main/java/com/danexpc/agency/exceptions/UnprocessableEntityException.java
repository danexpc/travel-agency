package com.danexpc.agency.exceptions;

public class UnprocessableEntityException extends RuntimeException {

    public static final String UNPROCESSABLE_ENTITY_EXCEPTION_MESSAGE = "Unable to process request %s";

    public UnprocessableEntityException(String message, Throwable cause) {
        super(message, cause);
    }
}

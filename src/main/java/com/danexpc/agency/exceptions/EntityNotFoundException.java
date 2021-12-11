package com.danexpc.agency.exceptions;

public class EntityNotFoundException extends RuntimeException {

    public static final String ENTITY_NOT_FOUND_EXCEPTION_MESSAGE = "Requested entity with id %d not found";
    public static final String ENTITY_WITH_EMAIL_NOT_FOUND_EXCEPTION_MESSAGE = "Requested entity with email %s not found";

    public EntityNotFoundException(String message) {
        super(message);
    }
}

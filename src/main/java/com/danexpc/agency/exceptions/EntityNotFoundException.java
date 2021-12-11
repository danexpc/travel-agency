package com.danexpc.agency.exceptions;

public class EntityNotFoundException extends RuntimeException {

    public static final String ENTITY_NOT_FOUND_EXCEPTION_MESSAGE = "Requested entity with id %d not found";

    public EntityNotFoundException(String message) {
        super(message);
    }
}

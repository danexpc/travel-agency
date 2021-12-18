package com.danexpc.agency.exceptions;

public class MissedParameterException extends RuntimeException {
    public static final String PARAMETER_IS_REQUIRED = "Parameter %s is required";

    public MissedParameterException(String message) {
        super(message);
    }
}

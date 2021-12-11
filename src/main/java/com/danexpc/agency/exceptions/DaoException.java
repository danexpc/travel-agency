package com.danexpc.agency.exceptions;

public class DaoException extends RuntimeException {

    public static final String DAO_EXCEPTION_MESSAGE = "Exception during query execution";

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }
}

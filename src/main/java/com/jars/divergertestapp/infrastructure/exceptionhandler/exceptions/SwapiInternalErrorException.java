package com.jars.divergertestapp.infrastructure.exceptionhandler.exceptions;

public class SwapiInternalErrorException extends RuntimeException {


    private static final String SWAPI_INTERNAL_ERROR_EXCEPTION_MESSAGE = "An internal error occurred while trying to access the SWAPI API";

    public SwapiInternalErrorException() {
        super(SWAPI_INTERNAL_ERROR_EXCEPTION_MESSAGE);
    }

    public SwapiInternalErrorException(String message) {
        super(message);
    }

}

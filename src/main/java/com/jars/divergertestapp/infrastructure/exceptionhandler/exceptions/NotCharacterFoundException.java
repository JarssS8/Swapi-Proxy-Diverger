package com.jars.divergertestapp.infrastructure.exceptionhandler.exceptions;

public class NotCharacterFoundException extends RuntimeException {

    private static final String NOT_SWAPI_CHARACTER_FOUND_EXCEPTION_MESSAGE = "There are not Star Wars characters in the API with the name provided";

    public NotCharacterFoundException() {
        super(NOT_SWAPI_CHARACTER_FOUND_EXCEPTION_MESSAGE);
    }

}

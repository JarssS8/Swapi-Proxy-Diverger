package com.jars.divergertestapp.infrastructure.exceptionhandler.exceptions;

public class MoreThanOneCharacterFoundException extends RuntimeException {

    private static final String MORE_THAN_ONE_CHARACTER_FOUND_EXCEPTION_MESSAGE = "There are more than one Star Wars characters in the API with the name provided";

    public MoreThanOneCharacterFoundException() {
        super(MORE_THAN_ONE_CHARACTER_FOUND_EXCEPTION_MESSAGE);
    }

}

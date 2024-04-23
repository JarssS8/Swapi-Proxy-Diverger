package com.jars.divergertestapp.infrastructure.exceptionhandler;


import com.jars.divergertestapp.infrastructure.exceptionhandler.exceptions.NotCharacterFoundException;
import com.jars.divergertestapp.infrastructure.exceptionhandler.exceptions.SwapiInternalErrorException;
import com.jars.divergertestapp.infrastructure.exceptionhandler.exceptions.MoreThanOneCharacterFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotCharacterFoundException.class)
    public ResponseEntity<Object> handleGeneralException(NotCharacterFoundException e, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        List<ErrorItem> errorItems = Stream.of(new ErrorItem("No Character found", "404",
                e.getMessage())).collect(Collectors.toList());
        return handleExceptionInternal(e, errorItems, headers, HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(MoreThanOneCharacterFoundException.class)
    public ResponseEntity<Object> handleGeneralException(MoreThanOneCharacterFoundException e, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        List<ErrorItem> errorItems = Stream.of(new ErrorItem("More than one Character found", "GL001",
                e.getMessage())).collect(Collectors.toList());
        return handleExceptionInternal(e, errorItems, headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(SwapiInternalErrorException.class)
    public ResponseEntity<Object> handleGeneralException(SwapiInternalErrorException e, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        List<ErrorItem> errorItems = Stream.of(new ErrorItem("Internal Swapi Error", "GL002",
                e.getMessage())).collect(Collectors.toList());
        return handleExceptionInternal(e, errorItems, headers, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleGeneralException(RuntimeException e, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        List<ErrorItem> errorItems = Stream.of(new ErrorItem("Runtime Exception", "GL000",
                e.getMessage())).collect(Collectors.toList());
        return handleExceptionInternal(e, errorItems, headers, HttpStatus.BAD_REQUEST, request);
    }

}

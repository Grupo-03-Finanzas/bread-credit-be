package com.finanzas.breadcredit.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ControllerError resourceNotFoundException(ResourceNotFoundException ex) {
        return new ControllerError(HttpStatus.NOT_FOUND, ex);
    }

    @ExceptionHandler(ResourceConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    ControllerError resourceNotFoundException(ResourceConflictException ex) {
        return new ControllerError(HttpStatus.CONFLICT, ex);
    }

    @ExceptionHandler(LoginException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    ControllerError loginException(LoginException ex) {
        return new ControllerError(HttpStatus.UNAUTHORIZED, ex);
    }

    @ExceptionHandler(UnexpectedException.class)
    @ResponseStatus(HttpStatus.OK)
    ControllerError unexpectedException(UnexpectedException ex) {
        return new ControllerError(HttpStatus.INTERNAL_SERVER_ERROR, ex);
    }

}

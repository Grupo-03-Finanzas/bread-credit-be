package com.finanzas.breadcredit.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ErrorResponse resourceNotFoundException(ResourceNotFoundException ex) {
        return new ErrorResponse(HttpStatus.NOT_FOUND, ex);
    }

    @ExceptionHandler(ResourceConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    ErrorResponse resourceNotFoundException(ResourceConflictException ex) {
        return new ErrorResponse(HttpStatus.CONFLICT, ex);
    }

    @ExceptionHandler(LoginException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    ErrorResponse loginException(LoginException ex) {
        return new ErrorResponse(HttpStatus.UNAUTHORIZED, ex);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    ErrorResponse unexpectedException(Exception ex) {
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex);
    }

}

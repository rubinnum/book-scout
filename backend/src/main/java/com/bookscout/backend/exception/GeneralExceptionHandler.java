package com.bookscout.backend.exception;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class GeneralExceptionHandler {

    @ExceptionHandler(value = {WrongCategoryException.class})
    public ResponseEntity<Object> handleException(WrongCategoryException e) {
        return handleBadRequestException(e);
    }

    @ExceptionHandler(value = {CategoryProgressDoesNotExistException.class})
    public ResponseEntity<Object> handleException(CategoryProgressDoesNotExistException e) {
        return handleBadRequestException(e);
    }

    private ResponseEntity<Object> handleBadRequestException(RuntimeException e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ExceptionResponse wrongCategoryResponse = new ExceptionResponse(
                e.getMessage(),
                badRequest,
                ZonedDateTime.now()
        );
        return new ResponseEntity<>(wrongCategoryResponse, badRequest);
    }
}

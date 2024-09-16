package com.bookscout.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class GeneralExceptionHandler {

    @ExceptionHandler(value = {WrongCategoryException.class})
    public ResponseEntity<Object> handleException(WrongCategoryException e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        WrongCategoryResponse wrongCategoryResponse = new WrongCategoryResponse(
                e.getMessage(),
                badRequest,
                ZonedDateTime.now()
        );
        return new ResponseEntity<>(wrongCategoryResponse, badRequest);
    }
}

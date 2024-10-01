package com.bookscout.backend.exception;

public class CategoryProgressDoesNotExistException extends RuntimeException {
    public CategoryProgressDoesNotExistException(String message) {
        super(message);
    }
}

package org.corywixom.cache.restws.api;

import org.corywixom.cache.core.exception.BadRequestException;
import org.corywixom.cache.core.exception.ForbiddenException;
import org.corywixom.cache.core.exception.InternalServerErrorException;
import org.corywixom.cache.core.exception.NotFoundException;
import org.corywixom.cache.core.exception.PayloadTooLargeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    private static final Logger LOG = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity handle(BadRequestException exception) {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(new ApiError(exception.getMessage()));
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity handle(ForbiddenException exception){
        return ResponseEntity
            .status(HttpStatus.FORBIDDEN)
            .body(new ApiError(exception.getMessage()));
    }

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity handle(InternalServerErrorException exception) {
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new ApiError(exception.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handle(Exception exception) {

        // These are unhandled exceptions that got thrown
        // outside that controller that don't get caught.
        // We want to ensure we log them.
        LOG.error(
            exception.getMessage(),
            exception
        );

        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("An unknown error occurred with the service call.");
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity handle(NotFoundException exception) {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(new ApiError(exception.getMessage()));
    }

    @ExceptionHandler(PayloadTooLargeException.class)
    public ResponseEntity handle(PayloadTooLargeException exception) {
        return ResponseEntity
            .status(HttpStatus.PAYLOAD_TOO_LARGE)
            .body(new ApiError(exception.getMessage()));
    }
}

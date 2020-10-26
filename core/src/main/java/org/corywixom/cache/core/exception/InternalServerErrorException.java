package org.corywixom.cache.core.exception;

public class InternalServerErrorException extends RuntimeException {
    public InternalServerErrorException(String message) {
        super(message);
    }
    
    public InternalServerErrorException(String message, Throwable causedBy) {
        super(message, causedBy);
    }
}

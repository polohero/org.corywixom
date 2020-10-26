package org.corywixom.cache.core.exception;

public class ForbiddenException extends RuntimeException {
    public ForbiddenException(String message) {
        super(message);
    }
    
    public ForbiddenException(String message, Throwable causedBy) {
        super(message, causedBy);
    }
}

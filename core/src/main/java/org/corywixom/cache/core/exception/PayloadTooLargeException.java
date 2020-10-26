package org.corywixom.cache.core.exception;

public class PayloadTooLargeException extends RuntimeException {
    
    public PayloadTooLargeException(String message) {
        super(message);
    }
    
    public PayloadTooLargeException(String message, Throwable causedBy) {
        super(message, causedBy);
    }
}

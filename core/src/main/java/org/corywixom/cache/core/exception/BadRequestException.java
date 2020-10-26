package org.corywixom.cache.core.exception;

public class BadRequestException extends RuntimeException{

    public BadRequestException(String message){
        super(message);
    }


    public BadRequestException(String message, Throwable causedBy){
        super(message, causedBy);
    }

}


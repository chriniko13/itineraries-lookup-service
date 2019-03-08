package com.adidas.chriniko.itinerarieslookupservice.error;

public class ProcessingException extends RuntimeException {

    public ProcessingException(String message, Throwable error) {
        super(message, error);
    }

}

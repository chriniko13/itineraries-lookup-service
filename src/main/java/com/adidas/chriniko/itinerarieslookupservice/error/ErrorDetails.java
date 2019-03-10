package com.adidas.chriniko.itinerarieslookupservice.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Getter
@RequiredArgsConstructor
public class ErrorDetails {

    private final Date timestamp;
    private final String message;
    private final String details;

}

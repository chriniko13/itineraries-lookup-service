package com.adidas.chriniko.itinerarieslookupservice.service;

import com.adidas.chriniko.itinerarieslookupservice.error.ProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Log4j2

@Component
public class DeepCopyProvider {

    private final ObjectMapper objectMapper;

    @Autowired
    public DeepCopyProvider(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public <T> List<T> deepCopy(List<T> input, Class<T> clazz) {
        try {

            String inputAsString = objectMapper.writeValueAsString(input);
            return objectMapper.readValue(inputAsString, objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));

        } catch (IOException error) {
            String msg = "deep copy operation failed";
            log.error(msg, error);
            throw new ProcessingException(msg, error);
        }
    }

}

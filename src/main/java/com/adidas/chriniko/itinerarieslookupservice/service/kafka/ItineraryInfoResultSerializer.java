package com.adidas.chriniko.itinerarieslookupservice.service.kafka;

import com.adidas.chriniko.itinerarieslookupservice.dto.ItineraryInfoResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

@Log4j2
public class ItineraryInfoResultSerializer implements Serializer<ItineraryInfoResult> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> map, boolean b) {
    }

    @Override
    public byte[] serialize(String topic, ItineraryInfoResult itineraryInfoResult) {
        try {
            return objectMapper.writeValueAsBytes(itineraryInfoResult);
        } catch (JsonProcessingException e) {
            log.error("could not serialize itineraryInfoResult", e);
            return null;
        }
    }

    @Override
    public void close() {
    }
}

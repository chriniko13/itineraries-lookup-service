package com.adidas.chriniko.itinerarieslookupservice.domain;

import com.adidas.chriniko.itinerarieslookupservice.serde.InstantSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

@Data
@RequiredArgsConstructor
public class ItineraryRouteInfo implements Serializable {

    private final String id;

    private final CityInfo city;
    private final CityInfo destinyCity;

    @JsonSerialize(using = InstantSerializer.class)
    private final Instant departureTime;

    @JsonSerialize(using = InstantSerializer.class)
    private final Instant arrivalTime;

}

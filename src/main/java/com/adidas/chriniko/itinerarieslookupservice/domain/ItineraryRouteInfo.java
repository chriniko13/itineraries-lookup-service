package com.adidas.chriniko.itinerarieslookupservice.domain;

import com.adidas.chriniko.itinerarieslookupservice.serde.InstantSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItineraryRouteInfo implements Serializable {

    private String id;

    private CityInfo city;
    private CityInfo destinyCity;

    @JsonSerialize(using = InstantSerializer.class)
    private Instant departureTime;

    @JsonSerialize(using = InstantSerializer.class)
    private Instant arrivalTime;

}

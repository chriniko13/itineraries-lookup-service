package com.adidas.chriniko.itinerarieslookupservice.domain;

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

    private final Instant departureTime;
    private final Instant arrivalTime;

}

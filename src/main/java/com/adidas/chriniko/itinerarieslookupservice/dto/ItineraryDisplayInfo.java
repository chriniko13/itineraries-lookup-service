package com.adidas.chriniko.itinerarieslookupservice.dto;

import com.adidas.chriniko.itinerarieslookupservice.domain.ItineraryRouteInfo;
import com.adidas.chriniko.itinerarieslookupservice.serde.DurationSerializer;
import com.adidas.chriniko.itinerarieslookupservice.serde.InstantSerializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ItineraryDisplayInfo implements Serializable {

    private String fastDisplay;

    private int noOfConnections;

    private int noOfVisitedCities;

    @JsonSerialize(using = InstantSerializer.class)
    private Instant departureTimeOfItinerary;

    @JsonSerialize(using = InstantSerializer.class)
    private Instant arrivalTimeOfItinerary;

    @JsonSerialize(using = DurationSerializer.class)
    private Duration timeDurationOfItinerary;

    private List<ItineraryRouteInfo> detailedRouteInfo;

}

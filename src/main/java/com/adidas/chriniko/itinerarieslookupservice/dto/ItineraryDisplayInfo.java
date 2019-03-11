package com.adidas.chriniko.itinerarieslookupservice.dto;

import com.adidas.chriniko.itinerarieslookupservice.domain.ItineraryRouteInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
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

    private Instant departureTimeOfItinerary;
    private Instant arrivalTimeOfItinerary;
    private Duration timeDurationOfItinerary;

    private List<ItineraryRouteInfo> detailedRouteInfo;

}

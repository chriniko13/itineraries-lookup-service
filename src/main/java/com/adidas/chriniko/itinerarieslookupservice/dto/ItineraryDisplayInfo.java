package com.adidas.chriniko.itinerarieslookupservice.dto;

import com.adidas.chriniko.itinerarieslookupservice.domain.ItineraryRouteInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ItineraryDisplayInfo {

    private String fastDisplay;

    private int noOfConnections;

    private int noOfVisitedCities;

    private List<ItineraryRouteInfo> detailedRouteInfo;

}

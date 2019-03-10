package com.adidas.chriniko.itinerarieslookupservice.dto;

import com.adidas.chriniko.itinerarieslookupservice.domain.ItineraryRouteInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ItineraryDisplayInfo {

    private String fastDisplay;

    private int noOfConnections;

    private int noOfVisitedCities;

    private List<ItineraryRouteInfo> detailedRouteInfo;

}

package com.adidas.chriniko.itinerarieslookupservice.dto;

import com.adidas.chriniko.itinerarieslookupservice.client.dto.RouteInfo;
import com.adidas.chriniko.itinerarieslookupservice.domain.ItineraryRouteInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ItineraryDisplayInfo {

    private String fastDisplay;
    private int noOfConnections;

    //private List<ItineraryRouteInfo> detailedRouteInfo;

    //TODO add more detailed views...

}

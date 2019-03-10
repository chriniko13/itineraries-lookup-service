package com.adidas.chriniko.itinerarieslookupservice.mapper;

import com.adidas.chriniko.itinerarieslookupservice.client.dto.RouteInfo;
import com.adidas.chriniko.itinerarieslookupservice.domain.ItineraryRouteInfo;
import org.springframework.stereotype.Component;

@Component
public class RouteInfoToItineraryRouteInfo {

    public ItineraryRouteInfo transform(RouteInfo input) {
        return new ItineraryRouteInfo(
                input.getId(),
                input.getCity(),
                input.getDestinyCity(),
                input.getDepartureTime(),
                input.getArrivalTime()
        );
    }
}

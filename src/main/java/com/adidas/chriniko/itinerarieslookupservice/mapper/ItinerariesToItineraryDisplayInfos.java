package com.adidas.chriniko.itinerarieslookupservice.mapper;

import com.adidas.chriniko.itinerarieslookupservice.domain.Itinerary;
import com.adidas.chriniko.itinerarieslookupservice.domain.ItineraryRouteInfo;
import com.adidas.chriniko.itinerarieslookupservice.dto.ItineraryDisplayInfo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ItinerariesToItineraryDisplayInfos {

    public List<ItineraryDisplayInfo> transform(List<Itinerary> itineraries) {
        return transform(itineraries, true);
    }

    public List<ItineraryDisplayInfo> transform(List<Itinerary> itineraries, boolean detailedRouteInfo) {
        return itineraries
                .stream()
                .map(itinerary -> {
                    ItineraryDisplayInfo itineraryDisplayInfo = new ItineraryDisplayInfo();

                    List<ItineraryRouteInfo> routesInfos = itinerary.getRoutesInfos();

                    String fastDisplay = calculateFastDisplay(routesInfos);
                    itineraryDisplayInfo.setFastDisplay(fastDisplay);

                    itineraryDisplayInfo.setNoOfConnections(routesInfos.size());

                    itineraryDisplayInfo.setNoOfVisitedCities(itineraryDisplayInfo.getNoOfConnections() + 1);

                    if (detailedRouteInfo) {
                        itineraryDisplayInfo.setDetailedRouteInfo(routesInfos);
                    }

                    return itineraryDisplayInfo;
                })
                .collect(Collectors.toList());
    }

    private String calculateFastDisplay(List<ItineraryRouteInfo> routesInfos) {

        final StringBuilder fastDisplay = new StringBuilder("[");

        for (int i = 0; i < routesInfos.size(); i++) {

            ItineraryRouteInfo itineraryRouteInfo = routesInfos.get(i);

            String originCityName = itineraryRouteInfo.getCity().getName();
            fastDisplay.append(originCityName).append(" ---> ");

            if (i == routesInfos.size() - 1) {
                String destinyCityName = itineraryRouteInfo.getDestinyCity().getName();
                fastDisplay.append(destinyCityName).append("]");
            }
        }

        return fastDisplay.toString();
    }


}

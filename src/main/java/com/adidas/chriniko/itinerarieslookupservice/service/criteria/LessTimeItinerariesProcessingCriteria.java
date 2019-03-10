package com.adidas.chriniko.itinerarieslookupservice.service.criteria;

import com.adidas.chriniko.itinerarieslookupservice.domain.Itinerary;
import com.adidas.chriniko.itinerarieslookupservice.domain.ItineraryRouteInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class LessTimeItinerariesProcessingCriteria implements ItinerariesProcessingCriteria {

    @Value("${itineraries-processing-criteria.less-time.result-limit-size}")
    private int limitSize;

    @Override
    public String itinerariesCriteriaName() {
        return "less-time";
    }

    @Override
    public List<Itinerary> process(List<Itinerary> input) {
        return input.stream()
                .sorted((i1, i2) -> {
                    Duration i1TotalDuration = findTimeDurationOfItinerary(i1);
                    Duration i2TotalDuration = findTimeDurationOfItinerary(i2);

                    return i1TotalDuration.compareTo(i2TotalDuration);
                })
                .limit(limitSize)
                .collect(Collectors.toList());
    }

    private Duration findTimeDurationOfItinerary(Itinerary itinerary) {
        List<ItineraryRouteInfo> routesInfos = itinerary.getRoutesInfos();

        ItineraryRouteInfo firstRouteInfo = routesInfos.get(0);
        ItineraryRouteInfo lastRouteInfo = routesInfos.get(routesInfos.size() - 1);

        return Duration.between(
                firstRouteInfo.getDepartureTime(),
                lastRouteInfo.getArrivalTime()
        );
    }

}

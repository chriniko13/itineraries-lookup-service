package com.adidas.chriniko.itinerarieslookupservice.mapper;

import com.adidas.chriniko.itinerarieslookupservice.domain.Itinerary;
import com.adidas.chriniko.itinerarieslookupservice.dto.ItineraryDisplayInfo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ItinerariesToItineraryDisplayInfos implements Mapper<Itinerary, ItineraryDisplayInfo> {

    @Override
    public List<ItineraryDisplayInfo> transform(List<Itinerary> itineraries) {
        return itineraries
                .stream()
                .map(itinerary -> {
                    ItineraryDisplayInfo itineraryDisplayInfo = new ItineraryDisplayInfo();

                    itineraryDisplayInfo.setNoOfConnections(itinerary.getRoutesInfos().size());

                    String fastDisplay = itinerary
                            .getRoutesInfos()
                            .stream()
                            .map(routeInfo -> routeInfo.getCity().getName())
                            .collect(Collectors.joining(" --> ", "[", "]"));

                    itineraryDisplayInfo.setFastDisplay(fastDisplay);

                    return itineraryDisplayInfo;
                })
                .collect(Collectors.toList());
    }

    @Override
    public ItineraryDisplayInfo transform(Itinerary input) {
        throw new UnsupportedOperationException("not needed at the moment");
    }


}

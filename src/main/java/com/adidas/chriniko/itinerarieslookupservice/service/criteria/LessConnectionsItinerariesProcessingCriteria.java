package com.adidas.chriniko.itinerarieslookupservice.service.criteria;

import com.adidas.chriniko.itinerarieslookupservice.domain.Itinerary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class LessConnectionsItinerariesProcessingCriteria implements ItinerariesProcessingCriteria {

    @Value("${itineraries-processing-criteria.less-connections.result-limit-size}")
    private int limitSize;


    @Override
    public String itinerariesCriteriaName() {
        return "less-connections";
    }

    @Override
    public List<Itinerary> process(List<Itinerary> input) {
        return input.stream()
                .sorted(Comparator.<Itinerary>comparingInt(i -> i.getRoutesInfos().size()))
                .limit(limitSize)
                .collect(Collectors.toList());
    }

}

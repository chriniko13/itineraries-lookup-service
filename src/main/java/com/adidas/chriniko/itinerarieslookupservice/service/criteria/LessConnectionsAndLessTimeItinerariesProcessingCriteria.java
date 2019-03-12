package com.adidas.chriniko.itinerarieslookupservice.service.criteria;

import com.adidas.chriniko.itinerarieslookupservice.domain.Itinerary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LessConnectionsAndLessTimeItinerariesProcessingCriteria extends ItinerariesProcessingCriteriaAndOperator {

    @Value("${itineraries-processing-criteria.less-connections-and-less-time.result-limit-size}")
    private int limitSize;

    @Autowired
    public LessConnectionsAndLessTimeItinerariesProcessingCriteria(LessConnectionsItinerariesProcessingCriteria lessConnections,
                                                                   LessTimeItinerariesProcessingCriteria lessTime) {
        super(lessConnections, lessTime);
    }

    @Override
    public String itinerariesCriteriaName() {
        return "less-connections-and-less-time";
    }

    @Override
    public List<Itinerary> process(List<Itinerary> input) {
        return super.process(input)
                .stream()
                .limit(limitSize)
                .collect(Collectors.toList());
    }
}

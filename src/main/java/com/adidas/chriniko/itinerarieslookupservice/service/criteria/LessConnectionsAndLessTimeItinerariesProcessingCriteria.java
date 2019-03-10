package com.adidas.chriniko.itinerarieslookupservice.service.criteria;

import com.adidas.chriniko.itinerarieslookupservice.domain.Itinerary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LessConnectionsAndLessTimeItinerariesProcessingCriteria extends ItinerariesProcessingCriteriaAndOperator {

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

        ItinerariesProcessingCriteria head = processingCriteria.get(0);
        List<ItinerariesProcessingCriteria> tail = processingCriteria.subList(1, processingCriteria.size());

        List<Itinerary> result = head.process(input);

        for (ItinerariesProcessingCriteria processingCriteria : tail) {
            result = processingCriteria.process(result);
        }

        return result;
    }
}

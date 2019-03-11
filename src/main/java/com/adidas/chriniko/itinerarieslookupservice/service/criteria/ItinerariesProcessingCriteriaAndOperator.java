package com.adidas.chriniko.itinerarieslookupservice.service.criteria;


import com.adidas.chriniko.itinerarieslookupservice.domain.Itinerary;

import java.util.Arrays;
import java.util.List;

public abstract class ItinerariesProcessingCriteriaAndOperator implements ItinerariesProcessingCriteria {

    protected List<ItinerariesProcessingCriteria> processingCriteria;

    public ItinerariesProcessingCriteriaAndOperator(ItinerariesProcessingCriteria... criteria) {
        processingCriteria = Arrays.asList(criteria);
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

package com.adidas.chriniko.itinerarieslookupservice.service.criteria;


import java.util.Arrays;
import java.util.List;

public abstract class ItinerariesProcessingCriteriaAndOperator implements ItinerariesProcessingCriteria {

    protected List<ItinerariesProcessingCriteria> processingCriteria;

    public ItinerariesProcessingCriteriaAndOperator(ItinerariesProcessingCriteria... criteria) {
        processingCriteria = Arrays.asList(criteria);
    }
}

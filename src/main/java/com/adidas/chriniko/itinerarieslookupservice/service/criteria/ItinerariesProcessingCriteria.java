package com.adidas.chriniko.itinerarieslookupservice.service.criteria;

import com.adidas.chriniko.itinerarieslookupservice.domain.Itinerary;

import java.util.List;

public interface ItinerariesProcessingCriteria {

    String itinerariesCriteriaName();

    List<Itinerary> process(List<Itinerary> input);
}

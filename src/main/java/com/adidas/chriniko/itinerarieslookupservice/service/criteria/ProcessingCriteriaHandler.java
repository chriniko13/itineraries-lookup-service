package com.adidas.chriniko.itinerarieslookupservice.service.criteria;

import com.adidas.chriniko.itinerarieslookupservice.domain.Itinerary;
import com.adidas.chriniko.itinerarieslookupservice.dto.ItineraryDisplayInfo;
import com.adidas.chriniko.itinerarieslookupservice.mapper.ItinerariesToItineraryDisplayInfos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProcessingCriteriaHandler {

    private final List<ItinerariesProcessingCriteria> itinerariesProcessingCriterias;
    private final ItinerariesToItineraryDisplayInfos itinerariesToItineraryDisplayInfos;

    @Autowired
    public ProcessingCriteriaHandler(List<ItinerariesProcessingCriteria> itinerariesProcessingCriterias,
                                     ItinerariesToItineraryDisplayInfos itinerariesToItineraryDisplayInfos) {
        this.itinerariesProcessingCriterias = itinerariesProcessingCriterias;
        this.itinerariesToItineraryDisplayInfos = itinerariesToItineraryDisplayInfos;
    }

    //TODO make it concurrent...
    public Map<String, List<ItineraryDisplayInfo>> process(List<Itinerary> itineraries) {

        final Map<String, List<ItineraryDisplayInfo>> itinerariesInfoByProcessingCriteria = new LinkedHashMap<>();

        for (ItinerariesProcessingCriteria itinerariesProcessingCriteria : itinerariesProcessingCriterias) {

            List<Itinerary> processedItineraries = itinerariesProcessingCriteria.process(itineraries);

            List<ItineraryDisplayInfo> displayInfos = itinerariesToItineraryDisplayInfos.transform(processedItineraries);

            itinerariesInfoByProcessingCriteria.put(
                    itinerariesProcessingCriteria.itinerariesCriteriaName(),
                    displayInfos
            );
        }

        return itinerariesInfoByProcessingCriteria;
    }


}

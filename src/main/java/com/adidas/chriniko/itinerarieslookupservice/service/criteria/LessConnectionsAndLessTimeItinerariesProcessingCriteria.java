package com.adidas.chriniko.itinerarieslookupservice.service.criteria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
}

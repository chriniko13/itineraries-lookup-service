package com.adidas.chriniko.itinerarieslookupservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ItineraryInfoResult {

    private List<ItineraryDisplayInfo> allItinerariesInfo;

    private Map<String /*processing criteria name*/, List<ItineraryDisplayInfo>> itinerariesInfoByProcessingCriteria;
}

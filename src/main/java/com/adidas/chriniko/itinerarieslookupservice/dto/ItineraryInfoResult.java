package com.adidas.chriniko.itinerarieslookupservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class ItineraryInfoResult {

    private List<ItineraryDisplayInfo> itinerariesInfo;
}

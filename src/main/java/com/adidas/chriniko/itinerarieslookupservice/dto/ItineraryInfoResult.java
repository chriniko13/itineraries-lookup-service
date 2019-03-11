package com.adidas.chriniko.itinerarieslookupservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ItineraryInfoResult implements Serializable {

    @ApiModelProperty(notes = "All itineraries info")
    private List<ItineraryDisplayInfo> allItinerariesInfo;

    @ApiModelProperty(notes = "Itineraries info by processing criteria")
    private Map<String /*processing criteria name*/, List<ItineraryDisplayInfo>> itinerariesInfoByProcessingCriteria;
}

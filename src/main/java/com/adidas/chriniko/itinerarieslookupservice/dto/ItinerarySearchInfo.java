package com.adidas.chriniko.itinerarieslookupservice.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItinerarySearchInfo {

    @ApiModelProperty(notes = "The city's name", required = true)
    @NotBlank
    private String name;

    @ApiModelProperty(notes = "The city's country name", required = true)
    @NotBlank
    private String country;

}

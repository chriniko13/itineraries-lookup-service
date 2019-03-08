package com.adidas.chriniko.itinerarieslookupservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityInfo {

    @NotBlank
    private String name;

    @NotBlank
    private String country;

}

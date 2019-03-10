package com.adidas.chriniko.itinerarieslookupservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityInfo {

    private String name;

    private String country;
}

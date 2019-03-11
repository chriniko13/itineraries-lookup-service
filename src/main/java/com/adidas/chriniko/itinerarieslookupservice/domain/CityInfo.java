package com.adidas.chriniko.itinerarieslookupservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityInfo implements Serializable {

    private String name;

    private String country;
}

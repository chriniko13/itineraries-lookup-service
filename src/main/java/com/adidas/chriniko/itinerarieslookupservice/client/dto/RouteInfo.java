package com.adidas.chriniko.itinerarieslookupservice.client.dto;

import com.adidas.chriniko.itinerarieslookupservice.domain.CityInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RouteInfo {

    private String id;

    private CityInfo city;
    private CityInfo destinyCity;

    private Instant departureTime;
    private Instant arrivalTime;
}

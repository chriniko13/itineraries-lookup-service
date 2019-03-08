package com.adidas.chriniko.itinerarieslookupservice.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RouteInfoResult {

    private List<RouteInfo> results;
}

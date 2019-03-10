package com.adidas.chriniko.itinerarieslookupservice.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class Itinerary {

    private final String rootRouteId;

    private final List<ItineraryRouteInfo> routesInfos;

    private String branchRouteId;

    private boolean traverseFinished;

    public Itinerary(String rootRouteId) {
        this.rootRouteId = rootRouteId;
        this.routesInfos = new LinkedList<>();
        this.branchRouteId = null;
        this.traverseFinished = false;
    }
}

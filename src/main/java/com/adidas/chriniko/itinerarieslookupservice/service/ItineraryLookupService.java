package com.adidas.chriniko.itinerarieslookupservice.service;

import com.adidas.chriniko.itinerarieslookupservice.client.connector.RoutesServiceConnector;
import com.adidas.chriniko.itinerarieslookupservice.client.dto.RouteInfo;
import com.adidas.chriniko.itinerarieslookupservice.client.dto.RouteInfoResult;
import com.adidas.chriniko.itinerarieslookupservice.dto.ItineraryInfo;
import com.adidas.chriniko.itinerarieslookupservice.dto.ItineraryInfoResult;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2

@Service
public class ItineraryLookupService {


    private final RoutesServiceConnector routesServiceConnector;

    @Autowired
    public ItineraryLookupService(RoutesServiceConnector routesServiceConnector) {
        this.routesServiceConnector = routesServiceConnector;
    }


    public ItineraryInfoResult process(ItineraryInfo itineraryInfo) {

        RouteInfoResult routeInfoResult = routesServiceConnector.search(itineraryInfo.getName(), itineraryInfo.getCountry());


        List<RouteInfo> routeInfos = routeInfoResult.getResults();

        for (RouteInfo routeInfo : routeInfos) {

            //TODO add logic...



        }




        return null;
    }
}

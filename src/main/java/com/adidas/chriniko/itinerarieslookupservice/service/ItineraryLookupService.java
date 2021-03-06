package com.adidas.chriniko.itinerarieslookupservice.service;

import com.adidas.chriniko.itinerarieslookupservice.client.connector.RoutesServiceConnector;
import com.adidas.chriniko.itinerarieslookupservice.client.dto.RouteInfo;
import com.adidas.chriniko.itinerarieslookupservice.client.dto.RouteInfoResult;
import com.adidas.chriniko.itinerarieslookupservice.domain.CityInfo;
import com.adidas.chriniko.itinerarieslookupservice.domain.Itinerary;
import com.adidas.chriniko.itinerarieslookupservice.domain.ItineraryRouteInfo;
import com.adidas.chriniko.itinerarieslookupservice.dto.ItineraryDisplayInfo;
import com.adidas.chriniko.itinerarieslookupservice.dto.ItineraryInfoResult;
import com.adidas.chriniko.itinerarieslookupservice.dto.ItinerarySearchInfo;
import com.adidas.chriniko.itinerarieslookupservice.error.ResourceNotFoundException;
import com.adidas.chriniko.itinerarieslookupservice.mapper.ItinerariesToItineraryDisplayInfos;
import com.adidas.chriniko.itinerarieslookupservice.mapper.RouteInfoToItineraryRouteInfo;
import com.adidas.chriniko.itinerarieslookupservice.service.criteria.ProcessingCriteriaHandler;
import com.adidas.chriniko.itinerarieslookupservice.service.interceptor.Report;
import com.adidas.chriniko.itinerarieslookupservice.service.provider.DeepCopyProvider;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Log4j2

@Service
public class ItineraryLookupService {

    private final RoutesServiceConnector routesServiceConnector;

    private final DeepCopyProvider deepCopyProvider;

    private final ProcessingCriteriaHandler processingCriteriaHandler;

    private final ItinerariesToItineraryDisplayInfos itinerariesToItineraryDisplayInfos;

    private final RouteInfoToItineraryRouteInfo routeInfoToItineraryRouteInfo;

    private final CacheService cacheService;

    @Autowired
    public ItineraryLookupService(RoutesServiceConnector routesServiceConnector,
                                  DeepCopyProvider deepCopyProvider,
                                  ProcessingCriteriaHandler processingCriteriaHandler,
                                  ItinerariesToItineraryDisplayInfos itinerariesToItineraryDisplayInfos,
                                  RouteInfoToItineraryRouteInfo routeInfoToItineraryRouteInfo,
                                  CacheService cacheService) {
        this.routesServiceConnector = routesServiceConnector;
        this.deepCopyProvider = deepCopyProvider;
        this.processingCriteriaHandler = processingCriteriaHandler;
        this.itinerariesToItineraryDisplayInfos = itinerariesToItineraryDisplayInfos;
        this.routeInfoToItineraryRouteInfo = routeInfoToItineraryRouteInfo;
        this.cacheService = cacheService;
    }

    @Report
    public ItineraryInfoResult process(ItinerarySearchInfo itinerarySearchInfo,
                                       boolean allItinerariesInfo,
                                       boolean allItinerariesInfoDetailed) {

        ItineraryInfoResult cached = cacheService.get(itinerarySearchInfo, allItinerariesInfo, allItinerariesInfoDetailed);
        if (cached != null) {
            return cached;
        }

        final ItineraryInfoResult itineraryInfoResult = new ItineraryInfoResult();

        // Note: fetch origin city/cities (root of itinerary/roots of itineraries) if exist
        RouteInfoResult routeInfoResult = routesServiceConnector.search(itinerarySearchInfo.getName(), itinerarySearchInfo.getCountry());
        List<RouteInfo> routeInfos = routeInfoResult.getResults();

        if (routeInfos.isEmpty()) {
            throw new ResourceNotFoundException("itinerary not exists for provided: " + itinerarySearchInfo);
        }

        final List<Itinerary> itineraries = initializeItinerariesState(routeInfos);

        calculateItineraries(itineraries);

        generateResponse(itineraryInfoResult, itineraries, allItinerariesInfo, allItinerariesInfoDetailed);

        cacheService.store(itinerarySearchInfo, allItinerariesInfo, allItinerariesInfoDetailed, itineraryInfoResult);

        return itineraryInfoResult;
    }

    private List<Itinerary> initializeItinerariesState(List<RouteInfo> routeInfos) {
        return routeInfos
                .stream()
                .map(routeInfo -> {
                    Itinerary itinerary = new Itinerary(routeInfo.getId());

                    ItineraryRouteInfo itineraryRouteInfo = routeInfoToItineraryRouteInfo.transform(routeInfo);
                    itinerary.getRoutesInfos().add(itineraryRouteInfo);

                    return itinerary;
                })
                .collect(Collectors.toList());
    }

    private void calculateItineraries(List<Itinerary> itineraries) {
        for (int i = 0
             ; shouldStopTraversing(itineraries)
                ; i = (i + 1) % itineraries.size()) {

            Itinerary itinerary = itineraries.get(i);

            String rootRouteId = itinerary.getRootRouteId();

            List<ItineraryRouteInfo> routes = itinerary.getRoutesInfos();

            ItineraryRouteInfo routeInfo = routes.get(routes.size() - 1);
            CityInfo destinyCity = routeInfo.getDestinyCity();

            // now use the destiny city in order to resolve the path of itinerary
            RouteInfoResult routesServiceResult = routesServiceConnector.search(destinyCity.getName(), destinyCity.getCountry());
            List<RouteInfo> routesServiceRouteInfos = routesServiceResult.getResults();

            if (routesServiceRouteInfos.isEmpty()) {
                itinerary.setTraverseFinished(true);
                continue;
            }

            if (routesServiceRouteInfos.size() == 1) {

                RouteInfo routesServiceRouteInfo = routesServiceRouteInfos.get(0);
                ItineraryRouteInfo nextRoute = routeInfoToItineraryRouteInfo.transform(routesServiceRouteInfo);
                routes.add(nextRoute);

            } else { // Note: branching case.

                RouteInfo head = routesServiceRouteInfos.get(0);
                List<RouteInfo> tail = routesServiceRouteInfos.subList(1, routesServiceRouteInfos.size());

                // for the tail, for every result we should create a copy in order to do the branching
                for (RouteInfo routesServiceRouteInfo : tail) {

                    List<ItineraryRouteInfo> deepCopyRoutes = deepCopyProvider.deepCopy(routes, ItineraryRouteInfo.class);

                    ItineraryRouteInfo nextRoute = routeInfoToItineraryRouteInfo.transform(routesServiceRouteInfo);

                    deepCopyRoutes.add(nextRoute);

                    String nextRouteId = nextRoute.getId();

                    Itinerary branchNewItinerary = new Itinerary(rootRouteId);
                    branchNewItinerary.setBranchRouteId(nextRouteId);
                    branchNewItinerary.getRoutesInfos().addAll(deepCopyRoutes);

                    itineraries.add(branchNewItinerary);
                }

                // for the head expand the already existing itinerary
                ItineraryRouteInfo nextRoute = routeInfoToItineraryRouteInfo.transform(head);
                routes.add(nextRoute);
            }
        }
    }

    private void generateResponse(ItineraryInfoResult itineraryInfoResult,
                                  List<Itinerary> itineraries,
                                  boolean allItinerariesInfo,
                                  boolean allItinerariesInfoDetailed) {

        if (allItinerariesInfo) {
            List<ItineraryDisplayInfo> allItineraryDisplayInfos = itinerariesToItineraryDisplayInfos.transform(itineraries, allItinerariesInfoDetailed);
            itineraryInfoResult.setAllItinerariesInfo(allItineraryDisplayInfos);
        }

        Map<String /*processing criteria name*/, List<ItineraryDisplayInfo>> itinerariesInfoByProcessingCriteria
                = processingCriteriaHandler.process(itineraries);
        itineraryInfoResult.setItinerariesInfoByProcessingCriteria(itinerariesInfoByProcessingCriteria);
    }

    private boolean shouldStopTraversing(List<Itinerary> itineraries) {

        List<Boolean> traverseStatuses = itineraries
                .stream()
                .map(Itinerary::isTraverseFinished)
                .collect(Collectors.toList());

        boolean allTraversesFinished = traverseStatuses.stream().allMatch(t -> t);

        return !allTraversesFinished;
    }

}

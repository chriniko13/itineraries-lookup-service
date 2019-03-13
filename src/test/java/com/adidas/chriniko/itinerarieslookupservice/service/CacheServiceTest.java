package com.adidas.chriniko.itinerarieslookupservice.service;

import com.adidas.chriniko.itinerarieslookupservice.dto.ItineraryDisplayInfo;
import com.adidas.chriniko.itinerarieslookupservice.dto.ItineraryInfoResult;
import com.adidas.chriniko.itinerarieslookupservice.dto.ItinerarySearchInfo;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.Duration;
import java.time.Instant;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class CacheServiceTest {

    @Mock
    HazelcastInstance hazelcastInstance;

    @Mock
    IMap<CacheService.ItinerarySearchInfoCacheInput, ItineraryInfoResult> map;

    private CacheService cacheService;

    @Before
    public void setUp() throws Exception {
        cacheService = new CacheService(hazelcastInstance);
    }

    @Test
    public void init() {

        // given
        Mockito.<Map<CacheService.ItinerarySearchInfoCacheInput, ItineraryInfoResult>>
                when(hazelcastInstance.getMap(Mockito.anyString()))
                .thenReturn(map);

        // when
        cacheService.init();

        // then
        Mockito.verify(hazelcastInstance).getMap(Mockito.anyString());
    }

    @Test
    public void get() {

        // given
        ItinerarySearchInfo itinerarySearchInfo = new ItinerarySearchInfo("Athens", "Greece");

        boolean allItinerariesInfo = false;
        boolean allItinerariesInfoDetailed = false;

        CacheService.ItinerarySearchInfoCacheInput input = new CacheService.ItinerarySearchInfoCacheInput(
                itinerarySearchInfo,
                allItinerariesInfo,
                allItinerariesInfoDetailed
        );

        Mockito.<Map<CacheService.ItinerarySearchInfoCacheInput, ItineraryInfoResult>>
                when(hazelcastInstance.getMap(Mockito.anyString()))
                .thenReturn(map);

        ItineraryInfoResult itineraryInfoResult = new ItineraryInfoResult();

        ItineraryDisplayInfo itineraryDisplayInfo = new ItineraryDisplayInfo();
        itineraryDisplayInfo.setFastDisplay("[Athens ---> Patra]");
        itineraryDisplayInfo.setNoOfConnections(1);
        itineraryDisplayInfo.setNoOfVisitedCities(2);

        itineraryDisplayInfo.setDepartureTimeOfItinerary(Instant.now());
        itineraryDisplayInfo.setArrivalTimeOfItinerary(Instant.now().plusSeconds(TimeUnit.SECONDS.convert(1, TimeUnit.DAYS)));
        itineraryDisplayInfo.setTimeDurationOfItinerary(Duration.between(itineraryDisplayInfo.getDepartureTimeOfItinerary(), itineraryDisplayInfo.getArrivalTimeOfItinerary()));

        itineraryDisplayInfo.setDetailedRouteInfo(Collections.emptyList());

        itineraryInfoResult.setAllItinerariesInfo(Collections.singletonList(itineraryDisplayInfo));
        itineraryInfoResult.setItinerariesInfoByProcessingCriteria(Collections.singletonMap("someCriteria", Collections.singletonList(itineraryDisplayInfo)));

        Mockito.when(map.get(input))
                .thenReturn(itineraryInfoResult);

        // when
        ItineraryInfoResult result = cacheService.get(itinerarySearchInfo, allItinerariesInfo, allItinerariesInfoDetailed);


        // then
        assertEquals(itineraryInfoResult, result);

        Mockito.verify(hazelcastInstance).getMap(Mockito.anyString());
        Mockito.verify(map).get(Mockito.any(CacheService.ItinerarySearchInfoCacheInput.class));


    }

    @Test
    public void store() {

        // given
        ItinerarySearchInfo itinerarySearchInfo = new ItinerarySearchInfo("Athens", "Greece");

        boolean allItinerariesInfo = false;
        boolean allItinerariesInfoDetailed = false;

        ItineraryInfoResult itineraryInfoResult = new ItineraryInfoResult();

        ItineraryDisplayInfo itineraryDisplayInfo = new ItineraryDisplayInfo();
        itineraryDisplayInfo.setFastDisplay("[Athens ---> Patra]");
        itineraryDisplayInfo.setNoOfConnections(1);
        itineraryDisplayInfo.setNoOfVisitedCities(2);

        itineraryDisplayInfo.setDepartureTimeOfItinerary(Instant.now());
        itineraryDisplayInfo.setArrivalTimeOfItinerary(Instant.now().plusSeconds(TimeUnit.SECONDS.convert(1, TimeUnit.DAYS)));
        itineraryDisplayInfo.setTimeDurationOfItinerary(Duration.between(itineraryDisplayInfo.getDepartureTimeOfItinerary(), itineraryDisplayInfo.getArrivalTimeOfItinerary()));

        itineraryDisplayInfo.setDetailedRouteInfo(Collections.emptyList());

        itineraryInfoResult.setAllItinerariesInfo(Collections.singletonList(itineraryDisplayInfo));
        itineraryInfoResult.setItinerariesInfoByProcessingCriteria(Collections.singletonMap("someCriteria", Collections.singletonList(itineraryDisplayInfo)));

        Mockito.<Map<CacheService.ItinerarySearchInfoCacheInput, ItineraryInfoResult>>
                when(hazelcastInstance.getMap(Mockito.anyString()))
                .thenReturn(map);


        // when
        cacheService.store(itinerarySearchInfo, allItinerariesInfo, allItinerariesInfoDetailed, itineraryInfoResult);

        // then
        Mockito.verify(hazelcastInstance).getMap(Mockito.anyString());
        Mockito.verify(map)
                .put(
                        Mockito.any(CacheService.ItinerarySearchInfoCacheInput.class),
                        Mockito.any(ItineraryInfoResult.class)
                );


    }
}
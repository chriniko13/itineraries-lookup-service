package com.adidas.chriniko.itinerarieslookupservice.service;

import com.adidas.chriniko.itinerarieslookupservice.configuration.HazelcastConfiguration;
import com.adidas.chriniko.itinerarieslookupservice.dto.ItineraryInfoResult;
import com.adidas.chriniko.itinerarieslookupservice.dto.ItinerarySearchInfo;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.Serializable;

@Log4j2

@Service
public class CacheService {

    private final HazelcastInstance hazelcastInstance;

    @Autowired
    public CacheService(HazelcastInstance hazelcastInstance) {
        this.hazelcastInstance = hazelcastInstance;
    }

    @PostConstruct
    void init() {
        IMap<ItinerarySearchInfoCacheInput, ItineraryInfoResult> itinerariesLookups = getMap();

        log.debug("total entries in itineraries-lookups map: {}", itinerariesLookups.size());

        itinerariesLookups.forEach((itinerarySearchInfo, itineraryInfoResult) -> {
            log.debug("{} ---> {}", itinerarySearchInfo, itineraryInfoResult);
        });
    }

    public ItineraryInfoResult get(ItinerarySearchInfo itinerarySearchInfo,
                                   boolean allItinerariesInfo,
                                   boolean allItinerariesInfoDetailed) {

        ItinerarySearchInfoCacheInput input = new ItinerarySearchInfoCacheInput(
                itinerarySearchInfo,
                allItinerariesInfo,
                allItinerariesInfoDetailed
        );

        return getMap().get(input);
    }

    public void store(ItinerarySearchInfo itinerarySearchInfo,
                      boolean allItinerariesInfo,
                      boolean allItinerariesInfoDetailed,
                      ItineraryInfoResult output) {

        IMap<ItinerarySearchInfoCacheInput, ItineraryInfoResult> itinerariesLookups = getMap();

        ItinerarySearchInfoCacheInput input = new ItinerarySearchInfoCacheInput(
                itinerarySearchInfo,
                allItinerariesInfo,
                allItinerariesInfoDetailed
        );

        itinerariesLookups.put(input, output);
    }

    private IMap<ItinerarySearchInfoCacheInput, ItineraryInfoResult> getMap() {
        return hazelcastInstance.getMap(HazelcastConfiguration.ITINERARIES_LOOKUPS);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ItinerarySearchInfoCacheInput implements Serializable {

        private ItinerarySearchInfo itinerarySearchInfo;
        private boolean allItinerariesInfo;
        private boolean allItinerariesInfoDetailed;
    }

}

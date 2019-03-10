package com.adidas.chriniko.itinerarieslookupservice.service.criteria;

import com.adidas.chriniko.itinerarieslookupservice.domain.Itinerary;
import com.adidas.chriniko.itinerarieslookupservice.dto.ItineraryDisplayInfo;
import com.adidas.chriniko.itinerarieslookupservice.error.ProcessingException;
import com.adidas.chriniko.itinerarieslookupservice.mapper.ItinerariesToItineraryDisplayInfos;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@Log4j2

@Component
public class ProcessingCriteriaHandler {

    private final List<ItinerariesProcessingCriteria> itinerariesProcessingCriterias;
    private final ItinerariesToItineraryDisplayInfos itinerariesToItineraryDisplayInfos;

    private ExecutorService itinerariesProcessingWorkers;

    @Autowired
    public ProcessingCriteriaHandler(List<ItinerariesProcessingCriteria> itinerariesProcessingCriterias,
                                     ItinerariesToItineraryDisplayInfos itinerariesToItineraryDisplayInfos) {
        this.itinerariesProcessingCriterias = itinerariesProcessingCriterias;
        this.itinerariesToItineraryDisplayInfos = itinerariesToItineraryDisplayInfos;
    }

    @PostConstruct
    public void init() {
        itinerariesProcessingWorkers = Executors.newFixedThreadPool(itinerariesProcessingCriterias.size(), new ThreadFactory() {
            private final AtomicInteger workerIdx = new AtomicInteger(0);

            @Override
            public Thread newThread(Runnable runnable) {
                Thread t = new Thread(runnable);
                t.setName("itinerary-processing-worker-" + workerIdx.incrementAndGet());
                return t;
            }
        });

        Runtime.getRuntime().addShutdownHook(new Thread(() -> itinerariesProcessingWorkers.shutdown()));
    }

    @PreDestroy
    public void cleanUp() {
        itinerariesProcessingWorkers.shutdown();
    }

    public Map<String, List<ItineraryDisplayInfo>> process(List<Itinerary> itineraries) {

        final CountDownLatch rendezvous = new CountDownLatch(itinerariesProcessingCriterias.size());

        final Map<String, List<ItineraryDisplayInfo>> itinerariesInfoByProcessingCriteria = new ConcurrentHashMap<>();

        for (ItinerariesProcessingCriteria itinerariesProcessingCriteria : itinerariesProcessingCriterias) {

            itinerariesProcessingWorkers.submit(() -> {

                List<Itinerary> processedItineraries = itinerariesProcessingCriteria.process(itineraries);

                List<ItineraryDisplayInfo> displayInfos = itinerariesToItineraryDisplayInfos.transform(processedItineraries);

                itinerariesInfoByProcessingCriteria.put(
                        itinerariesProcessingCriteria.itinerariesCriteriaName(),
                        displayInfos
                );

                rendezvous.countDown();

            });

        }

        try {
            rendezvous.await(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            String msg = "itineraries processing operation failed";
            log.error(msg, e);
            throw new ProcessingException(msg, e);
        }

        return itinerariesInfoByProcessingCriteria;
    }


}

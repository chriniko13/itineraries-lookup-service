package com.adidas.chriniko.itinerarieslookupservice.service.kafka;


import com.adidas.chriniko.itinerarieslookupservice.dto.ItineraryInfoResult;
import com.adidas.chriniko.itinerarieslookupservice.dto.ItinerarySearchInfo;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.stereotype.Component;

import java.util.Properties;
import java.util.concurrent.CompletableFuture;

@Log4j2

@Component
public class ReportProducer {

    private static final String TOPIC_NAME = "itineraries-lookup-reports";
    private static final String BOOTSTRAP_SERVERS = "localhost:9092";
    private final KafkaProducer<ItinerarySearchInfo, ItineraryInfoResult> producer;


    public ReportProducer() {
        Properties props = new Properties();

        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "KafkaExampleProducer");

        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, ItinerarySearchInfoSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, ItineraryInfoResultSerializer.class.getName());

        producer = new KafkaProducer<>(props);
    }

    public CompletableFuture<RecordMetadata> send(ItinerarySearchInfo itinerarySearchInfo,
                                                  ItineraryInfoResult itineraryInfoResult) {

        CompletableFuture<RecordMetadata> cf = new CompletableFuture<>();

        ProducerRecord<ItinerarySearchInfo, ItineraryInfoResult> record
                = new ProducerRecord<>(TOPIC_NAME, itinerarySearchInfo, itineraryInfoResult);

        producer.send(record, (metadata, exception) -> {
            if (exception != null) {
                log.error("could not send message to kafka", exception);
                cf.completeExceptionally(exception);
            } else {
                log.info("successful send message to kafka, metadata: {}", metadata);
                cf.complete(metadata);
            }
        });

        return cf;
    }


}

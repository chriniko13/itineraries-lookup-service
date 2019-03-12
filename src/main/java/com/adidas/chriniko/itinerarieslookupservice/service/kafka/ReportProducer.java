package com.adidas.chriniko.itinerarieslookupservice.service.kafka;


import com.adidas.chriniko.itinerarieslookupservice.dto.ItineraryInfoResult;
import com.adidas.chriniko.itinerarieslookupservice.dto.ItinerarySearchInfo;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;

@Log4j2

@Component
public class ReportProducer {

    @Value("${report-producer.topic-name}")
    private String topicName;

    @Value("${report-producer.bootstrap-servers}")
    private String bootstrapServers;

    private KafkaProducer<ItinerarySearchInfo, ItineraryInfoResult> producer;

    @PostConstruct
    public void init() {
        Properties props = new Properties();

        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "KafkaExampleProducer");

        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, ItinerarySearchInfoSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, ItineraryInfoResultSerializer.class.getName());

        producer = new KafkaProducer<>(props);
    }

    public CompletableFuture<RecordMetadata> send(ItinerarySearchInfo itinerarySearchInfo,
                                                  ItineraryInfoResult itineraryInfoResult) {

        CompletableFuture<RecordMetadata> cf = new CompletableFuture<>();

        ProducerRecord<ItinerarySearchInfo, ItineraryInfoResult> record
                = new ProducerRecord<>(topicName, itinerarySearchInfo, itineraryInfoResult);

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

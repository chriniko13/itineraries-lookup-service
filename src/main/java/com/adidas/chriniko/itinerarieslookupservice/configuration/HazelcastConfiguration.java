package com.adidas.chriniko.itinerarieslookupservice.configuration;

import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MaxSizeConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.management.ManagementFactory;

@Configuration
public class HazelcastConfiguration {

    public static final String ITINERARIES_LOOKUPS = "itineraries-lookups";

    private static final String INSTANCE_NAME = "itineraries-lookup-service@"
            + ManagementFactory.getRuntimeMXBean().getName()
            + " --- hazelcast-instance";

    @Bean
    public Config hazelCastConfig() {

        Config config = new Config();

        config.setInstanceName(INSTANCE_NAME)
                .addMapConfig(
                        new MapConfig()
                                .setName(ITINERARIES_LOOKUPS)
                                .setMaxSizeConfig(new MaxSizeConfig(200, MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE))
                                .setEvictionPolicy(EvictionPolicy.LRU)
                                .setTimeToLiveSeconds(60 * 2)
                );

        return config;
    }
}

package com.adidas.chriniko.itinerarieslookupservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(
        exclude = {
                SecurityAutoConfiguration.class,
        }
)
public class ItinerariesLookupServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItinerariesLookupServiceApplication.class, args);
    }

}

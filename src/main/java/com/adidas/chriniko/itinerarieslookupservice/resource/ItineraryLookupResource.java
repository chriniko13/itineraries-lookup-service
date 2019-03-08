package com.adidas.chriniko.itinerarieslookupservice.resource;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;

@Log4j2

@RestController
@RequestMapping("/api/itinerary-info")
public class ItineraryLookupResource {


    @GetMapping(
            path = "hello",
            produces = MediaType.TEXT_PLAIN_VALUE
    )
    @ResponseBody String hello() {

        log.debug("  >> hello");
        return "Hello";
    }

}

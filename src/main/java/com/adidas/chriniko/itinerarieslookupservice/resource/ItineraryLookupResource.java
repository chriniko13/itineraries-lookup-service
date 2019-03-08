package com.adidas.chriniko.itinerarieslookupservice.resource;

import com.adidas.chriniko.itinerarieslookupservice.dto.ItineraryInfo;
import com.adidas.chriniko.itinerarieslookupservice.dto.ItineraryInfoResult;
import com.adidas.chriniko.itinerarieslookupservice.service.ItineraryLookupService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Log4j2

@RestController
@RequestMapping("/api/itinerary-info")
public class ItineraryLookupResource {

    private final ItineraryLookupService itineraryLookupService;

    @Autowired
    public ItineraryLookupResource(ItineraryLookupService itineraryLookupService) {
        this.itineraryLookupService = itineraryLookupService;
    }

    @GetMapping(
            path = "hello",
            produces = MediaType.TEXT_PLAIN_VALUE
    )
    @ResponseBody
    String hello() {
        log.debug("  >> hello");
        return "Hello";
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public @ResponseBody
    HttpEntity<ItineraryInfoResult> find(@RequestBody @Valid ItineraryInfo input) {
        log.debug("  >> find: {}", input);

        ItineraryInfoResult result = itineraryLookupService.process(input);
        return ResponseEntity.ok(result);
    }


}

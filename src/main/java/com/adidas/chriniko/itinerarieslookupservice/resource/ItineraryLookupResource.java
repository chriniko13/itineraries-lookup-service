package com.adidas.chriniko.itinerarieslookupservice.resource;

import com.adidas.chriniko.itinerarieslookupservice.dto.ItineraryInfoResult;
import com.adidas.chriniko.itinerarieslookupservice.dto.ItinerarySearchInfo;
import com.adidas.chriniko.itinerarieslookupservice.service.ItineraryLookupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Log4j2

@Api(value = "ItineraryLookupResource", description = "Itinerary lookup operations for provided --> (city,country)")

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


    @ApiOperation(value = "Lookup itineraries for provided city", response = ItineraryInfoResult.class)
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Successfully calculated itineraries")
            }
    )
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public @ResponseBody
    HttpEntity<ItineraryInfoResult> find(@RequestBody @Valid ItinerarySearchInfo input,
                                         @RequestParam(name = "allItinerariesInfo", required = false, defaultValue = "false") boolean allItinerariesInfo,
                                         @RequestParam(name = "allItinerariesInfoDetailed", required = false, defaultValue = "false") boolean allItinerariesInfoDetailed) {
        log.debug("  >> find: {} --- allItinerariesInfo: {}", input, allItinerariesInfo);

        ItineraryInfoResult result = itineraryLookupService.process(input, allItinerariesInfo, allItinerariesInfoDetailed);
        return ResponseEntity.ok(result);
    }


}

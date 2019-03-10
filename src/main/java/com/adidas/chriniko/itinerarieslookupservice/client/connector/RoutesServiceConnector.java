package com.adidas.chriniko.itinerarieslookupservice.client.connector;

import com.adidas.chriniko.itinerarieslookupservice.domain.CityInfo;
import com.adidas.chriniko.itinerarieslookupservice.client.dto.RouteInfoResult;
import com.adidas.chriniko.itinerarieslookupservice.error.ProcessingException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Log4j2

@Component
public class RoutesServiceConnector {

    @Value("${routes-service.search-url}")
    private String searchUrl;

    private final RestTemplate restTemplate;

    @Autowired
    public RoutesServiceConnector(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Retryable(
            value = {ProcessingException.class},
            maxAttempts = 5,
            backoff = @Backoff(delay = 70, maxDelay = 200, multiplier = 1.25, random = true)
    )
    public RouteInfoResult search(String city, String country) {

        CityInfo cityInfo = new CityInfo(city, country);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json");

        HttpEntity<CityInfo> httpEntity = new HttpEntity<>(cityInfo, httpHeaders);

        try {
            ResponseEntity<RouteInfoResult> response = restTemplate.exchange(searchUrl, HttpMethod.POST, httpEntity, RouteInfoResult.class);

            RouteInfoResult routeInfoResult = response.getBody();

            log.debug("routes service search result: {}", routeInfoResult);

            return routeInfoResult;

        } catch (RestClientException error) {
            String msg = "could not perform search operation to routes-service";
            log.error(msg, error);
            throw new ProcessingException(msg, error);
        }

    }

}

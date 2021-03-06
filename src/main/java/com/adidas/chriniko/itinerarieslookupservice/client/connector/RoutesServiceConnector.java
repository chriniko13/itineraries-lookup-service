package com.adidas.chriniko.itinerarieslookupservice.client.connector;

import com.adidas.chriniko.itinerarieslookupservice.client.dto.RouteInfoResult;
import com.adidas.chriniko.itinerarieslookupservice.domain.CityInfo;
import com.adidas.chriniko.itinerarieslookupservice.error.ProcessingException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.util.List;

@Log4j2

@Component
public class RoutesServiceConnector {

    @Value("${routes-service.search-url}")
    private String searchUrl;

    @Value("${routes-service.security.username}")
    private String username;

    @Value("${routes-service.security.password}")
    private String password;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final BasicAuthProvider basicAuthProvider;

    @Autowired
    public RoutesServiceConnector(RestTemplate restTemplate,
                                  ObjectMapper objectMapper,
                                  BasicAuthProvider basicAuthProvider) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
        this.basicAuthProvider = basicAuthProvider;
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

        List<String> basicAuthHeaderInfo = basicAuthProvider.getBasicAuthHeader(username, password);
        httpHeaders.add(basicAuthHeaderInfo.get(0), basicAuthHeaderInfo.get(1));

        HttpEntity<CityInfo> httpEntity = new HttpEntity<>(cityInfo, httpHeaders);

        try {
            ResponseEntity<RouteInfoResult> response = restTemplate.exchange(searchUrl, HttpMethod.POST, httpEntity, RouteInfoResult.class);

            RouteInfoResult routeInfoResult = response.getBody();

            try {
                log.debug("request: {} --- response: {}", cityInfo, objectMapper.writeValueAsString(routeInfoResult));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            return routeInfoResult;

        } catch (RestClientException error) {
            String msg = "could not perform search operation to routes-service";
            log.error(msg, error);
            throw new ProcessingException(msg, error);
        }

    }

}

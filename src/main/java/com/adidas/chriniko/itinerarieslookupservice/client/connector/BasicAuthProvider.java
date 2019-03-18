package com.adidas.chriniko.itinerarieslookupservice.client.connector;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@Component
public class BasicAuthProvider {

    public List<String> getBasicAuthHeader(String username, String password) {

        String plainClientCredentials = username + ":" + password;
        String base64ClientCredentials = Base64.getEncoder().encodeToString(plainClientCredentials.getBytes());

        return Arrays.asList("Authorization", "Basic " + base64ClientCredentials);
    }

}

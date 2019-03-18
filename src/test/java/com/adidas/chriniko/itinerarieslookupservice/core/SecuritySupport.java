package com.adidas.chriniko.itinerarieslookupservice.core;


import java.util.Base64;


public class SecuritySupport {

    public static String[] getBasicAuthHeader(String username, String password) {

        String plainClientCredentials = username + ":" + password;
        String base64ClientCredentials = Base64.getEncoder().encodeToString(plainClientCredentials.getBytes());

        return new String[]{"Authorization", "Basic " + base64ClientCredentials};
    }

}

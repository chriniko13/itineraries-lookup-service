package com.adidas.chriniko.itinerarieslookupservice.service;

import com.adidas.chriniko.itinerarieslookupservice.client.connector.RoutesServiceConnector;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ItineraryLookupServiceTest {

    private ItineraryLookupService itineraryLookupService;

    @Mock
    private RoutesServiceConnector routesServiceConnector;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        itineraryLookupService = new ItineraryLookupService(routesServiceConnector);
    }

    @Test
    public void foo() {
        Assert.assertEquals(1, 1);
    }
}
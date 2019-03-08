package com.adidas.chriniko.itinerarieslookupservice.it;

import com.adidas.chriniko.itinerarieslookupservice.ItinerariesLookupServiceApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = ItinerariesLookupServiceApplication.class,
        properties = {"application.properties"}
)

@RunWith(SpringRunner.class)
public class SpecificationIT {

    @LocalServerPort
    private int port;


    @Test
    public void foo() {
        Assert.assertEquals(1, 1);
    }
}

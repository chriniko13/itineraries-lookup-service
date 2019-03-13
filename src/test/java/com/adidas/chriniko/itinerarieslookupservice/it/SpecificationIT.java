package com.adidas.chriniko.itinerarieslookupservice.it;

import com.adidas.chriniko.itinerarieslookupservice.ItinerariesLookupServiceApplication;
import com.adidas.chriniko.itinerarieslookupservice.dto.ItineraryInfoResult;
import com.adidas.chriniko.itinerarieslookupservice.dto.ItinerarySearchInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.github.tomakehurst.wiremock.matching.EqualToJsonPattern;
import com.google.common.io.Resources;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = ItinerariesLookupServiceApplication.class,
        properties = {"application.properties"}
)

@RunWith(SpringRunner.class)
public class SpecificationIT {

    @LocalServerPort
    private int port;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8080);

    @Test
    public void itinerary_search_works_as_expected_allItinerariesInfo_false_and_allItinerariesInfoDetailed_false_case() throws Exception {
        // given
        initWiremockResponses();

        ItinerarySearchInfo itinerarySearchInfo = new ItinerarySearchInfo("Tarragona", "Spain");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json");
        HttpEntity<ItinerarySearchInfo> httpEntity = new HttpEntity<>(itinerarySearchInfo, httpHeaders);

        // when
        ResponseEntity<ItineraryInfoResult> responseEntity = restTemplate.exchange("http://localhost:" + port + "/api/itinerary-info?allItinerariesInfo=false&allItinerariesInfoDetailed=false",
                HttpMethod.POST,
                httpEntity,
                ItineraryInfoResult.class
        );


        // then
        Assert.assertNotNull(responseEntity);

        ItineraryInfoResult result = responseEntity.getBody();

        String expectedAsString = Resources.toString(
                this.getClass().getResource("/itinerary_search_works_as_expected_case/responses/response_tarragona_spain.json"),
                Charset.forName("UTF-8")
        );

        ItineraryInfoResult expected = objectMapper.readValue(expectedAsString, ItineraryInfoResult.class);

        Assert.assertEquals(expected, result);
    }

    @Test
    public void itinerary_search_works_as_expected_allItinerariesInfo_true_and_allItinerariesInfoDetailed_false_case() throws Exception {
        // given
        initWiremockResponses();

        ItinerarySearchInfo itinerarySearchInfo = new ItinerarySearchInfo("Tarragona", "Spain");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json");
        HttpEntity<ItinerarySearchInfo> httpEntity = new HttpEntity<>(itinerarySearchInfo, httpHeaders);

        // when
        ResponseEntity<ItineraryInfoResult> responseEntity = restTemplate.exchange("http://localhost:" + port + "/api/itinerary-info?allItinerariesInfo=true&allItinerariesInfoDetailed=false",
                HttpMethod.POST,
                httpEntity,
                ItineraryInfoResult.class
        );

        // then
        Assert.assertNotNull(responseEntity);

        ItineraryInfoResult result = responseEntity.getBody();

        String expectedAsString = Resources.toString(
                this.getClass().getResource("/itinerary_search_works_as_expected_case/responses/response_tarragona_spain_2.json"),
                Charset.forName("UTF-8")
        );

        ItineraryInfoResult expected = objectMapper.readValue(expectedAsString, ItineraryInfoResult.class);

        Assert.assertEquals(expected, result);
    }

    @Test
    public void itinerary_search_works_as_expected_allItinerariesInfo_true_and_allItinerariesInfoDetailed_true_case() throws Exception {
        // given
        initWiremockResponses();

        ItinerarySearchInfo itinerarySearchInfo = new ItinerarySearchInfo("Tarragona", "Spain");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json");
        HttpEntity<ItinerarySearchInfo> httpEntity = new HttpEntity<>(itinerarySearchInfo, httpHeaders);

        // when
        ResponseEntity<ItineraryInfoResult> responseEntity = restTemplate.exchange("http://localhost:" + port + "/api/itinerary-info?allItinerariesInfo=true&allItinerariesInfoDetailed=true",
                HttpMethod.POST,
                httpEntity,
                ItineraryInfoResult.class
        );

        // then
        Assert.assertNotNull(responseEntity);

        ItineraryInfoResult result = responseEntity.getBody();


        String expectedAsString = Resources.toString(
                this.getClass().getResource("/itinerary_search_works_as_expected_case/responses/response_tarragona_spain_3.json"),
                Charset.forName("UTF-8")
        );

        ItineraryInfoResult expected = objectMapper.readValue(expectedAsString, ItineraryInfoResult.class);

        Assert.assertEquals(expected, result);
    }

    private void initWiremockResponses() {

        // 1
        stubFor(post(urlEqualTo("/api/route-info/search"))
                .withHeader("Content-Type", equalTo("application/json"))
                .withRequestBody(new EqualToJsonPattern("{\n" +
                        "\t\"name\":\"Tarragona\",\n" +
                        "\t\"country\":\"Spain\"\n" +
                        "}", true, false))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json;charset=UTF-8")
                        .withBody("{\"results\":[{\"id\":\"222e1394-d884-4488-8eb8-09b363a688f9\",\"city\":{\"name\":\"Tarragona\",\"country\":\"Spain\"},\"destinyCity\":{\"name\":\"Jaen\",\"country\":\"Spain\"},\"departureTime\":\"2019-03-13T00:09:59Z\",\"arrivalTime\":\"2019-03-13T04:09:59Z\"},{\"id\":\"6e8e753d-3b94-4d77-8362-2950a6b6a0d5\",\"city\":{\"name\":\"Tarragona\",\"country\":\"Spain\"},\"destinyCity\":{\"name\":\"Merida\",\"country\":\"Spain\"},\"departureTime\":\"2019-03-13T00:09:59Z\",\"arrivalTime\":\"2019-03-13T03:09:59Z\"},{\"id\":\"8f2e304a-e89b-4c42-a6ee-b6a1452629da\",\"city\":{\"name\":\"Tarragona\",\"country\":\"Spain\"},\"destinyCity\":{\"name\":\"La Coruna\",\"country\":\"Spain\"},\"departureTime\":\"2019-03-13T00:09:59Z\",\"arrivalTime\":\"2019-03-13T02:09:59Z\"},{\"id\":\"f3c05c7d-6b22-4fec-98bd-42eb30dd3490\",\"city\":{\"name\":\"Tarragona\",\"country\":\"Spain\"},\"destinyCity\":{\"name\":\"Arrecife\",\"country\":\"Spain\"},\"departureTime\":\"2019-03-13T00:09:59Z\",\"arrivalTime\":\"2019-03-13T02:09:59Z\"},{\"id\":\"fec3084a-45e2-4577-9daa-f4a34160904e\",\"city\":{\"name\":\"Tarragona\",\"country\":\"Spain\"},\"destinyCity\":{\"name\":\"Vitoria\",\"country\":\"Spain\"},\"departureTime\":\"2019-03-13T00:09:59Z\",\"arrivalTime\":\"2019-03-13T01:09:59Z\"}]}")));
        // 2
        stubFor(post(urlEqualTo("/api/route-info/search"))
                .withHeader("Content-Type", equalTo("application/json"))
                .withRequestBody(new EqualToJsonPattern("{\n" +
                        "\t\"name\":\"Jaen\",\n" +
                        "\t\"country\":\"Spain\"\n" +
                        "}", true, false))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json;charset=UTF-8")
                        .withBody("{\"results\":[]}")));
        // 3
        stubFor(post(urlEqualTo("/api/route-info/search"))
                .withHeader("Content-Type", equalTo("application/json"))
                .withRequestBody(new EqualToJsonPattern("{\n" +
                        "\t\"name\":\"Merida\",\n" +
                        "\t\"country\":\"Spain\"\n" +
                        "}", true, false))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json;charset=UTF-8")
                        .withBody("{\"results\":[{\"id\":\"87e48b18-640f-44b1-9fa8-c6ae03672e48\",\"city\":{\"name\":\"Merida\",\"country\":\"Spain\"},\"destinyCity\":{\"name\":\"Algeciras\",\"country\":\"Spain\"},\"departureTime\":\"2019-03-13T03:09:59Z\",\"arrivalTime\":\"2019-03-13T05:09:59Z\"}]}")));
        // 4
        stubFor(post(urlEqualTo("/api/route-info/search"))
                .withHeader("Content-Type", equalTo("application/json"))
                .withRequestBody(new EqualToJsonPattern("{\n" +
                        "\t\"name\":\"La Coruna\",\n" +
                        "\t\"country\":\"Spain\"\n" +
                        "}", true, false))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json;charset=UTF-8")
                        .withBody("{\"results\":[{\"id\":\"3e8a8e24-a8b5-4fb7-8a68-25006224acf3\",\"city\":{\"name\":\"La Coruna\",\"country\":\"Spain\"},\"destinyCity\":{\"name\":\"Ourense\",\"country\":\"Spain\"},\"departureTime\":\"2019-03-13T02:09:59Z\",\"arrivalTime\":\"2019-03-13T05:09:59Z\"}]}")));

        // 5
        stubFor(post(urlEqualTo("/api/route-info/search"))
                .withHeader("Content-Type", equalTo("application/json"))
                .withRequestBody(new EqualToJsonPattern("{\n" +
                        "\t\"name\":\"Arrecife\",\n" +
                        "\t\"country\":\"Spain\"\n" +
                        "}", true, false))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json;charset=UTF-8")
                        .withBody("{\"results\":[{\"id\":\"c2f67e40-fe91-4f72-9049-9c37fe0270dd\",\"city\":{\"name\":\"Arrecife\",\"country\":\"Spain\"},\"destinyCity\":{\"name\":\"Las Palmas\",\"country\":\"Spain\"},\"departureTime\":\"2019-03-13T02:09:59Z\",\"arrivalTime\":\"2019-03-13T03:09:59Z\"}]}")));
        // 6
        stubFor(post(urlEqualTo("/api/route-info/search"))
                .withHeader("Content-Type", equalTo("application/json"))
                .withRequestBody(new EqualToJsonPattern("{\n" +
                        "\t\"name\":\"Vitoria\",\n" +
                        "\t\"country\":\"Spain\"\n" +
                        "}", true, false))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json;charset=UTF-8")
                        .withBody("{\"results\":[{\"id\":\"febd8ead-891c-4ae5-ad09-d6ba800605df\",\"city\":{\"name\":\"Vitoria\",\"country\":\"Spain\"},\"destinyCity\":{\"name\":\"Santander\",\"country\":\"Spain\"},\"departureTime\":\"2019-03-13T01:09:59Z\",\"arrivalTime\":\"2019-03-13T03:09:59Z\"}]}")));
        // 7
        stubFor(post(urlEqualTo("/api/route-info/search"))
                .withHeader("Content-Type", equalTo("application/json"))
                .withRequestBody(new EqualToJsonPattern("{\n" +
                        "\t\"name\":\"Jaen\",\n" +
                        "\t\"country\":\"Spain\"\n" +
                        "}", true, false))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json;charset=UTF-8")
                        .withBody("{\"results\":[]}")));
        // 8
        stubFor(post(urlEqualTo("/api/route-info/search"))
                .withHeader("Content-Type", equalTo("application/json"))
                .withRequestBody(new EqualToJsonPattern("{\n" +
                        "\t\"name\":\"Algeciras\",\n" +
                        "\t\"country\":\"Spain\"\n" +
                        "}", true, false))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json;charset=UTF-8")
                        .withBody("{\"results\":[{\"id\":\"ce69a92a-0653-497f-9a07-0ea8f09f8237\",\"city\":{\"name\":\"Algeciras\",\"country\":\"Spain\"},\"destinyCity\":{\"name\":\"Marbella\",\"country\":\"Spain\"},\"departureTime\":\"2019-03-13T05:09:59Z\",\"arrivalTime\":\"2019-03-13T09:09:59Z\"}]}")));
        // 9
        stubFor(post(urlEqualTo("/api/route-info/search"))
                .withHeader("Content-Type", equalTo("application/json"))
                .withRequestBody(new EqualToJsonPattern("{\n" +
                        "\t\"name\":\"Ourense\",\n" +
                        "\t\"country\":\"Spain\"\n" +
                        "}", true, false))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json;charset=UTF-8")
                        .withBody("{\"results\":[{\"id\":\"cbe14205-addd-45d2-9cab-8bf18fb09f65\",\"city\":{\"name\":\"Ourense\",\"country\":\"Spain\"},\"destinyCity\":{\"name\":\"Bilbao\",\"country\":\"Spain\"},\"departureTime\":\"2019-03-13T05:09:59Z\",\"arrivalTime\":\"2019-03-13T06:09:59Z\"}]}")));
        // 10
        stubFor(post(urlEqualTo("/api/route-info/search"))
                .withHeader("Content-Type", equalTo("application/json"))
                .withRequestBody(new EqualToJsonPattern("{\n" +
                        "\t\"name\":\"Las Palmas\",\n" +
                        "\t\"country\":\"Spain\"\n" +
                        "}", true, false))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json;charset=UTF-8")
                        .withBody("{\"results\":[]}")));
        // 11
        stubFor(post(urlEqualTo("/api/route-info/search"))
                .withHeader("Content-Type", equalTo("application/json"))
                .withRequestBody(new EqualToJsonPattern("{\n" +
                        "\t\"name\":\"Santander\",\n" +
                        "\t\"country\":\"Spain\"\n" +
                        "}", true, false))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json;charset=UTF-8")
                        .withBody("{\"results\":[{\"id\":\"5a4ef1dd-045b-4efc-a0f1-c31a3824a0b0\",\"city\":{\"name\":\"Santander\",\"country\":\"Spain\"},\"destinyCity\":{\"name\":\"Logrono\",\"country\":\"Spain\"},\"departureTime\":\"2019-03-13T03:09:59Z\",\"arrivalTime\":\"2019-03-13T04:09:59Z\"}]}")));
        // 12
        stubFor(post(urlEqualTo("/api/route-info/search"))
                .withHeader("Content-Type", equalTo("application/json"))
                .withRequestBody(new EqualToJsonPattern("{\n" +
                        "\t\"name\":\"Jaen\",\n" +
                        "\t\"country\":\"Spain\"\n" +
                        "}", true, false))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json;charset=UTF-8")
                        .withBody("{\"results\":[]}")));
        // 13
        stubFor(post(urlEqualTo("/api/route-info/search"))
                .withHeader("Content-Type", equalTo("application/json"))
                .withRequestBody(new EqualToJsonPattern("{\n" +
                        "\t\"name\":\"Marbella\",\n" +
                        "\t\"country\":\"Spain\"\n" +
                        "}", true, false))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json;charset=UTF-8")
                        .withBody("{\"results\":[{\"id\":\"ddf16a25-3986-44f0-a558-dbf3a99e00f6\",\"city\":{\"name\":\"Marbella\",\"country\":\"Spain\"},\"destinyCity\":{\"name\":\"Almeria\",\"country\":\"Spain\"},\"departureTime\":\"2019-03-13T09:09:59Z\",\"arrivalTime\":\"2019-03-13T13:09:59Z\"}]}")));
        // 14
        stubFor(post(urlEqualTo("/api/route-info/search"))
                .withHeader("Content-Type", equalTo("application/json"))
                .withRequestBody(new EqualToJsonPattern("{\n" +
                        "\t\"name\":\"Bilbao\",\n" +
                        "\t\"country\":\"Spain\"\n" +
                        "}", true, false))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json;charset=UTF-8")
                        .withBody("{\"results\":[{\"id\":\"df4fd978-76a9-4a9f-aab3-bb9a5dd0c464\",\"city\":{\"name\":\"Bilbao\",\"country\":\"Spain\"},\"destinyCity\":{\"name\":\"Santa Cruz de Tenerife\",\"country\":\"Spain\"},\"departureTime\":\"2019-03-13T06:09:59Z\",\"arrivalTime\":\"2019-03-13T07:09:59Z\"}]}")));
        // 15
        stubFor(post(urlEqualTo("/api/route-info/search"))
                .withHeader("Content-Type", equalTo("application/json"))
                .withRequestBody(new EqualToJsonPattern("{\n" +
                        "\t\"name\":\"Las Palmas\",\n" +
                        "\t\"country\":\"Spain\"\n" +
                        "}", true, false))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json;charset=UTF-8")
                        .withBody("{\"results\":[]}")));
        // 16
        stubFor(post(urlEqualTo("/api/route-info/search"))
                .withHeader("Content-Type", equalTo("application/json"))
                .withRequestBody(new EqualToJsonPattern("{\n" +
                        "\t\"name\":\"Logrono\",\n" +
                        "\t\"country\":\"Spain\"\n" +
                        "}", true, false))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json;charset=UTF-8")
                        .withBody("{\"results\":[{\"id\":\"fcef99aa-fb75-45d4-8e11-e1ede770d23e\",\"city\":{\"name\":\"Logrono\",\"country\":\"Spain\"},\"destinyCity\":{\"name\":\"Barcelona\",\"country\":\"Spain\"},\"departureTime\":\"2019-03-13T04:09:59Z\",\"arrivalTime\":\"2019-03-13T08:09:59Z\"}]}")));
        // 17
        stubFor(post(urlEqualTo("/api/route-info/search"))
                .withHeader("Content-Type", equalTo("application/json"))
                .withRequestBody(new EqualToJsonPattern("{\n" +
                        "\t\"name\":\"Jaen\",\n" +
                        "\t\"country\":\"Spain\"\n" +
                        "}", true, false))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json;charset=UTF-8")
                        .withBody("{\"results\":[]}")));
        // 18
        stubFor(post(urlEqualTo("/api/route-info/search"))
                .withHeader("Content-Type", equalTo("application/json"))
                .withRequestBody(new EqualToJsonPattern("{\n" +
                        "\t\"name\":\"Almeria\",\n" +
                        "\t\"country\":\"Spain\"\n" +
                        "}", true, false))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json;charset=UTF-8")
                        .withBody("{\"results\":[{\"id\":\"87868690-109c-4e80-95b8-29b2d88b3ed9\",\"city\":{\"name\":\"Almeria\",\"country\":\"Spain\"},\"destinyCity\":{\"name\":\"Cordoba\",\"country\":\"Spain\"},\"departureTime\":\"2019-03-13T13:09:59Z\",\"arrivalTime\":\"2019-03-13T14:09:59Z\"}]}")));
        // 19
        stubFor(post(urlEqualTo("/api/route-info/search"))
                .withHeader("Content-Type", equalTo("application/json"))
                .withRequestBody(new EqualToJsonPattern("{\n" +
                        "\t\"name\":\"Santa Cruz de Tenerife\",\n" +
                        "\t\"country\":\"Spain\"\n" +
                        "}", true, false))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json;charset=UTF-8")
                        .withBody("{\"results\":[{\"id\":\"6143f03a-bb3e-47fe-b540-e00deb76593a\",\"city\":{\"name\":\"Santa Cruz de Tenerife\",\"country\":\"Spain\"},\"destinyCity\":{\"name\":\"Seville\",\"country\":\"Spain\"},\"departureTime\":\"2019-03-13T07:09:59Z\",\"arrivalTime\":\"2019-03-13T11:09:59Z\"}]}")));
        // 20
        stubFor(post(urlEqualTo("/api/route-info/search"))
                .withHeader("Content-Type", equalTo("application/json"))
                .withRequestBody(new EqualToJsonPattern("{\n" +
                        "\t\"name\":\"Las Palmas\",\n" +
                        "\t\"country\":\"Spain\"\n" +
                        "}", true, false))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json;charset=UTF-8")
                        .withBody("{\"results\":[]}")));
        // 21
        stubFor(post(urlEqualTo("/api/route-info/search"))
                .withHeader("Content-Type", equalTo("application/json"))
                .withRequestBody(new EqualToJsonPattern("{\n" +
                        "\t\"name\":\"Barcelona\",\n" +
                        "\t\"country\":\"Spain\"\n" +
                        "}", true, false))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json;charset=UTF-8")
                        .withBody("{\"results\":[{\"id\":\"622a93b3-9b5e-4634-8d08-f5776fe5ec68\",\"city\":{\"name\":\"Barcelona\",\"country\":\"Spain\"},\"destinyCity\":{\"name\":\"Mataro\",\"country\":\"Spain\"},\"departureTime\":\"2019-03-13T08:09:59Z\",\"arrivalTime\":\"2019-03-13T09:09:59Z\"}]}")));
        // 22
        stubFor(post(urlEqualTo("/api/route-info/search"))
                .withHeader("Content-Type", equalTo("application/json"))
                .withRequestBody(new EqualToJsonPattern("{\n" +
                        "\t\"name\":\"Jaen\",\n" +
                        "\t\"country\":\"Spain\"\n" +
                        "}", true, false))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json;charset=UTF-8")
                        .withBody("{\"results\":[]}")));
        // 23
        stubFor(post(urlEqualTo("/api/route-info/search"))
                .withHeader("Content-Type", equalTo("application/json"))
                .withRequestBody(new EqualToJsonPattern("{\n" +
                        "\t\"name\":\"Cordoba\",\n" +
                        "\t\"country\":\"Spain\"\n" +
                        "}", true, false))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json;charset=UTF-8")
                        .withBody("{\"results\":[]}")));
        // 24
        stubFor(post(urlEqualTo("/api/route-info/search"))
                .withHeader("Content-Type", equalTo("application/json"))
                .withRequestBody(new EqualToJsonPattern("{\n" +
                        "\t\"name\":\"Seville\",\n" +
                        "\t\"country\":\"Spain\"\n" +
                        "}", true, false))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json;charset=UTF-8")
                        .withBody("{\"results\":[]}")));
        // 25
        stubFor(post(urlEqualTo("/api/route-info/search"))
                .withHeader("Content-Type", equalTo("application/json"))
                .withRequestBody(new EqualToJsonPattern("{\n" +
                        "\t\"name\":\"Las Palmas\",\n" +
                        "\t\"country\":\"Spain\"\n" +
                        "}", true, false))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json;charset=UTF-8")
                        .withBody("{\"results\":[]}")));
        // 26
        stubFor(post(urlEqualTo("/api/route-info/search"))
                .withHeader("Content-Type", equalTo("application/json"))
                .withRequestBody(new EqualToJsonPattern("{\n" +
                        "\t\"name\":\"Mataro\",\n" +
                        "\t\"country\":\"Spain\"\n" +
                        "}", true, false))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json;charset=UTF-8")
                        .withBody("{\"results\":[]}")));
    }
}

package com.jars.divergertestapp.infrastructure.external;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class SwapiHttpClientTest {

    @Autowired
    private SwapiHttpClient swapiHttpClient;

    @MockBean
    private RestTemplate restTemplate;

    @Value("${swapi.url}")
    private String SWAPI_BASE_URL;

    @Test
    void testGetSwapiResourceFromApi() {
        String url = SWAPI_BASE_URL + "people?search=Luke";
        String expectedResponse = "{\n" +
                "    \"count\": 1,\n" +
                "    \"next\": null,\n" +
                "    \"previous\": null,\n" +
                "    \"results\": [\n" +
                "        {\n" +
                "            \"name\": \"Luke Skywalker\",\n" +
                "            \"height\": \"172\",\n" +
                "            \"mass\": \"77\",\n" +
                "            \"hair_color\": \"blond\",\n" +
                "            \"skin_color\": \"fair\",\n" +
                "            \"eye_color\": \"blue\",\n" +
                "            \"birth_year\": \"19BBY\",\n" +
                "            \"gender\": \"male\",\n" +
                "            \"homeworld\": \"https://swapi.py4e.com/api/planets/1/\",\n" +
                "            \"films\": [\n" +
                "                \"https://swapi.py4e.com/api/films/1/\",\n" +
                "                \"https://swapi.py4e.com/api/films/2/\",\n" +
                "                \"https://swapi.py4e.com/api/films/3/\",\n" +
                "                \"https://swapi.py4e.com/api/films/6/\",\n" +
                "                \"https://swapi.py4e.com/api/films/7/\"\n" +
                "            ],\n" +
                "            \"species\": [\n" +
                "                \"https://swapi.py4e.com/api/species/1/\"\n" +
                "            ],\n" +
                "            \"vehicles\": [\n" +
                "                \"https://swapi.py4e.com/api/vehicles/14/\",\n" +
                "                \"https://swapi.py4e.com/api/vehicles/30/\"\n" +
                "            ],\n" +
                "            \"starships\": [\n" +
                "                \"https://swapi.py4e.com/api/starships/12/\",\n" +
                "                \"https://swapi.py4e.com/api/starships/22/\"\n" +
                "            ],\n" +
                "            \"created\": \"2014-12-09T13:50:51.644000Z\",\n" +
                "            \"edited\": \"2014-12-20T21:17:56.891000Z\",\n" +
                "            \"url\": \"https://swapi.py4e.com/api/people/1/\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";

        when(restTemplate.getForEntity(url, String.class))
                .thenReturn(ResponseEntity.ok(expectedResponse));

        String response = swapiHttpClient.getSwapiResourceFromApi(url);
        assertEquals(expectedResponse, response);
    }
}

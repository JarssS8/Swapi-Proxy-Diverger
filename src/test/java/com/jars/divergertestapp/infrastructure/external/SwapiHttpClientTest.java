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
        String expectedResponse = """
                {
                    "count": 1,
                    "next": null,
                    "previous": null,
                    "results": [
                        {
                            "name": "Luke Skywalker",
                            "height": "172",
                            "mass": "77",
                            "hair_color": "blond",
                            "skin_color": "fair",
                            "eye_color": "blue",
                            "birth_year": "19BBY",
                            "gender": "male",
                            "homeworld": "https://swapi.py4e.com/api/planets/1/",
                            "films": [
                                "https://swapi.py4e.com/api/films/1/",
                                "https://swapi.py4e.com/api/films/2/",
                                "https://swapi.py4e.com/api/films/3/",
                                "https://swapi.py4e.com/api/films/6/",
                                "https://swapi.py4e.com/api/films/7/"
                            ],
                            "species": [
                                "https://swapi.py4e.com/api/species/1/"
                            ],
                            "vehicles": [
                                "https://swapi.py4e.com/api/vehicles/14/",
                                "https://swapi.py4e.com/api/vehicles/30/"
                            ],
                            "starships": [
                                "https://swapi.py4e.com/api/starships/12/",
                                "https://swapi.py4e.com/api/starships/22/"
                            ],
                            "created": "2014-12-09T13:50:51.644000Z",
                            "edited": "2014-12-20T21:17:56.891000Z",
                            "url": "https://swapi.py4e.com/api/people/1/"
                        }
                    ]
                }""";

        when(restTemplate.getForEntity(url, String.class))
                .thenReturn(ResponseEntity.ok(expectedResponse));

        String response = swapiHttpClient.getSwapiResourceFromApi(url);
        assertEquals(expectedResponse, response);
    }
}

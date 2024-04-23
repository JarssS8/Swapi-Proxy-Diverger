package com.jars.divergertestapp.infrastructure.external;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SwapiUrlBuilderTest {

    @Autowired
    private SwapiUrlBuilder swapiUrlBuilder;

    @Value("${swapi.url}")
    private String SWAPI_BASE_URL;

    @Test
    void testBuildCharacterSearchUrl() {
        String url = swapiUrlBuilder.buildCharacterSearchUrl("Luke");
        assertEquals(SWAPI_BASE_URL + "people?search=Luke", url);
    }
}

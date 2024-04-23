package com.jars.divergertestapp.infrastructure.external;

import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SwapiUrlBuilder {

    @Value("${swapi.url}")
    private String SWAPI_BASE_URL;

    private final String SWAPI_PEOPLE_PATH = "people";

    private final String SWAPI_SEARCH_QUERY_PARAM = "search";

    public String buildCharacterSearchUrl(String name) {
        return UriComponentsBuilder.fromHttpUrl(SWAPI_BASE_URL)
                .path(SWAPI_PEOPLE_PATH)
                .queryParam(SWAPI_SEARCH_QUERY_PARAM, name)
                .toUriString();
    }
}

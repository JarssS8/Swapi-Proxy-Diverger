package com.jars.divergertestapp.infrastructure.external;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jars.divergertestapp.domain.model.*;
import com.jars.divergertestapp.domain.model.Character;
import io.swagger.v3.core.util.Json;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@SpringBootTest
class SwapiClientTest {

    @Autowired
    private SwapiClient swapiClient;

    @MockBean
    private SwapiUrlBuilder swapiUrlBuilder;

    @MockBean
    private SwapiHttpClient swapiHttpClient;

    @MockBean
    private SwapiJsonParser swapiJsonParser;


    @Test
    void create_character_test() throws JsonProcessingException {
        JSONObject characterJson = new JSONObject();
        characterJson.put("name", "Luke Skywalker");
        characterJson.put("homeworld", "https://swapi.dev/api/planets/1/");
        characterJson.put("starships", List.of("https://swapi.dev/api/starships/12/"));
        characterJson.put("vehicles", List.of("https://swapi.dev/api/vehicles/14/"));
        when(swapiJsonParser.createObject(characterJson, Character.class)).thenReturn(Character.builder()
                .name("Luke Skywalker")
                .homeWorldUrl("https://swapi.dev/api/planets/1/")
                .starships(List.of("https://swapi.dev/api/starships/12/"))
                .vehicles(List.of("https://swapi.dev/api/vehicles/14/", "https://swapi.dev/api/vehicles/15/", "https://swapi.dev/api/vehicles/16/"))
                .build());
        when(swapiJsonParser.createObjectFromUrl("https://swapi.dev/api/planets/1/", Planet.class)).thenReturn(Planet.builder().name("Tatooine").build());
        Character character = swapiClient.createCharacter(characterJson);

        assertEquals("Luke Skywalker", character.getName());
        assertEquals("https://swapi.dev/api/planets/1/", character.getHomeWorldUrl());
        assertEquals(1, character.getStarships().size());
        assertEquals(3, character.getVehicles().size());

    }

    @Test
    void get_swapi_character_from_api_test() throws ParseException {
        String name = "Luke";
        String url = "https://swapi.dev/api/people/?search=Luke";
        String response = "{\"count\":1,\"results\":[{\"name\":\"Luke Skywalker\",\"homeworld\":\"https://swapi.dev/api/planets/1/\",\"starships\":[\"https://swapi.dev/api/starships/12/\"],\"vehicles\":[\"https://swapi.dev/api/vehicles/14/\"]}]}";
        JSONObject jsonObject = (JSONObject) new JSONParser().parse(response);
        JSONArray resultsArray = (JSONArray) jsonObject.get("results");
        JSONObject characterJson = (JSONObject) resultsArray.get(0);
        when(swapiUrlBuilder.buildCharacterSearchUrl(name)).thenReturn(url);
        when(swapiHttpClient.getSwapiResourceFromApi(url)).thenReturn(response);
        when(swapiJsonParser.parseJson(response)).thenReturn(jsonObject);
        try {
            when(swapiJsonParser.createObject(characterJson, Character.class)).thenReturn(Character.builder()
                    .name("Luke Skywalker")
                    .homeWorldUrl("https://swapi.dev/api/planets/1/")
                    .starships(List.of("https://swapi.dev/api/starships/12/"))
                    .vehicles(List.of("https://swapi.dev/api/vehicles/14/", "https://swapi.dev/api/vehicles/15/", "https://swapi.dev/api/vehicles/16/"))
                    .build());

            when(swapiJsonParser.createObjectFromUrl("https://swapi.dev/api/planets/1/", Planet.class)).thenReturn(Planet.builder().name("Tatooine").build());
            Character character = swapiClient.getSwapiCharacterFromApi(name).get();
            assertEquals("Luke Skywalker", character.getName());
            assertEquals("https://swapi.dev/api/planets/1/", character.getHomeWorldUrl());
            assertEquals(1, character.getStarships().size());
            assertEquals(3, character.getVehicles().size());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
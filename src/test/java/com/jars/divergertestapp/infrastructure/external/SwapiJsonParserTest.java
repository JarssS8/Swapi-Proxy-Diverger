package com.jars.divergertestapp.infrastructure.external;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;
import com.jars.divergertestapp.domain.model.Character;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class SwapiJsonParserTest {

    @Autowired
    private SwapiJsonParser swapiJsonParser;

    @MockBean
    private ObjectMapper objectMapper;

    @MockBean
    private SwapiHttpClient swapiHttpClient;

    @Test
    void test_parse_json() {
        String json = "{\"key\":\"value\"}";
        JSONObject jsonObject = swapiJsonParser.parseJson(json);
        assertEquals("value", jsonObject.get("key"));
    }

    @Test
    void test_create_object_from_url() throws JsonProcessingException {
        String url = "http://swapi.dev/api/people/1/";
        String json = "{\"name\":\"Luke Skywalker\"}";
        Character character = new Character();
        character.setName("Luke Skywalker");

        when(swapiHttpClient.getSwapiResourceFromApi(url)).thenReturn(json);
        when(objectMapper.readValue(json, Character.class)).thenReturn(character);

        Character result = swapiJsonParser.createObjectFromUrl(url, Character.class);
        assertEquals(character, result);
    }

    @Test
    void tes_create_object() throws JsonProcessingException {
        String json = "{\"name\":\"Luke Skywalker\"}";
        JSONObject jsonObject = swapiJsonParser.parseJson(json);
        Character character = new Character();
        character.setName("Luke Skywalker");

        when(objectMapper.readValue(json, Character.class)).thenReturn(character);

        Character result = swapiJsonParser.createObject(jsonObject, Character.class);
        assertEquals(character, result);
    }

    @Test
    void test_create_list() throws JsonProcessingException {
        List<String> urls = Arrays.asList("http://swapi.dev/api/people/1/", "http://swapi.dev/api/people/2/");
        String json1 = "{\"name\":\"Luke Skywalker\"}";
        String json2 = "{\"name\":\"Leia Organa\"}";
        Character character1 = new Character();
        character1.setName("Luke Skywalker");
        Character character2 = new Character();
        character2.setName("Leia Organa");

        when(swapiHttpClient.getSwapiResourceFromApi(urls.get(0))).thenReturn(json1);
        when(swapiHttpClient.getSwapiResourceFromApi(urls.get(1))).thenReturn(json2);
        when(objectMapper.readValue(json1, Character.class)).thenReturn(character1);
        when(objectMapper.readValue(json2, Character.class)).thenReturn(character2);

        List<Character> result = swapiJsonParser.createList(urls, Character.class);
        assertEquals(Arrays.asList(character1, character2), result);
    }
}

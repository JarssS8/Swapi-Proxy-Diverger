package com.jars.divergertestapp.infrastructure.external;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jars.divergertestapp.domain.model.Character;
import com.jars.divergertestapp.domain.model.*;
import com.jars.divergertestapp.infrastructure.exceptionhandler.exceptions.MoreThanOneCharacterFoundException;
import com.jars.divergertestapp.infrastructure.exceptionhandler.exceptions.NotCharacterFoundException;
import com.jars.divergertestapp.infrastructure.exceptionhandler.exceptions.SwapiInternalErrorException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class SwapiClient {

    @Autowired
    private SwapiUrlBuilder swapiUrlBuilder;

    @Autowired
    private SwapiHttpClient swapiHttpClient;

    @Autowired
    private SwapiJsonParser swapiJsonParser;

    public Optional<Character> getSwapiCharacterFromApi(String name) {
        String url = swapiUrlBuilder.buildCharacterSearchUrl(name);
        String response = swapiHttpClient.getSwapiResourceFromApi(url);
        JSONObject jsonObject = swapiJsonParser.parseJson(response);

        if (jsonObject == null) {
            throw new SwapiInternalErrorException("Failed to parse JSON response");
        }

        int count = Integer.parseInt(jsonObject.get("count").toString());
        if (count == 0) {
            throw new NotCharacterFoundException();
        } else if (count > 1) {
            throw new MoreThanOneCharacterFoundException();
        }

        JSONArray resultsArray = (JSONArray) jsonObject.get("results");
        JSONObject characterJson = (JSONObject) resultsArray.get(0);

        try {
            Character character = createCharacter(characterJson);
            return Optional.of(character);
        } catch (JsonProcessingException e) {
            throw new SwapiInternalErrorException("Failed to parse character data");
        }
    }

    public Character createCharacter(JSONObject characterJson) throws JsonProcessingException {
        Character character = swapiJsonParser.createObject(characterJson, Character.class);

        if(character.getHomeWorldUrl() != null && !character.getHomeWorldUrl().isEmpty())
            character.setPlanet(swapiJsonParser.createObjectFromUrl(character.getHomeWorldUrl(), Planet.class));

        if(character.getFilms() != null && !character.getFilms().isEmpty())
            character.setFilmsList(swapiJsonParser.createList(character.getFilms(), Film.class));
        if(character.getStarships() != null && !character.getStarships().isEmpty())
            character.setStarshipsList(swapiJsonParser.createList(character.getStarships(), StarshipVehicle.class));
        if(character.getVehicles() != null && !character.getVehicles().isEmpty())
            character.setVehiclesList(swapiJsonParser.createList(character.getVehicles(), TerrainVehicle.class));

        return character;
    }
}
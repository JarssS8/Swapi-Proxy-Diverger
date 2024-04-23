package com.jars.divergertestapp.infrastructure.external;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SwapiJsonParser {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SwapiHttpClient swapiHttpClient;

    public JSONObject parseJson(String json) {
        return (JSONObject) JSONValue.parse(json);
    }

    public <T> T createObjectFromUrl(String url, Class<T> classBase) throws JsonProcessingException {
        return objectMapper.readValue(swapiHttpClient.getSwapiResourceFromApi(url), classBase);
    }

    public <T> T createObject(JSONObject jsonObject, Class<T> classBase) throws JsonProcessingException {
        return objectMapper.readValue(jsonObject.toJSONString(), classBase);
    }


    public <T> List<T> createList(List<String> urls, Class<T> classBase) throws JsonProcessingException {
        List<T> list = new ArrayList<>();
        for (String url : urls) {
            list.add(createObjectFromUrl(url, classBase));
        }
        return list;
    }
}

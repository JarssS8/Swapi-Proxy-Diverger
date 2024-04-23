package com.jars.divergertestapp.infrastructure.external;

import com.jars.divergertestapp.infrastructure.exceptionhandler.exceptions.SwapiInternalErrorException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class SwapiHttpClient {

    @Autowired
    private RestTemplate restTemplate;

    public String getSwapiResourceFromApi(String url) {
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        if (!response.getStatusCode().is2xxSuccessful() || response.getBody() == null){
            throw new SwapiInternalErrorException("Failed to fetch data from SWAPI");
        }
        return response.getBody();
    }

}

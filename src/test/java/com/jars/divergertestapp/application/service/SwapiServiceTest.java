package com.jars.divergertestapp.application.service;


import com.jars.divergertestapp.application.dto.CharacterDto;
import com.jars.divergertestapp.domain.model.Character;
import com.jars.divergertestapp.domain.model.Planet;
import com.jars.divergertestapp.infrastructure.external.SwapiClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class SwapiServiceTest {

    @Autowired
    private SwapiService swapiService;

    @MockBean
    private SwapiClient swapiClient;

    @Test
    void testGetSwapiCharacter() {
        String name = "Luke";
        Character character = Character.builder()
                .name("Luke Skywalker")
                .starshipsList(new ArrayList<>())
                .vehiclesList(new ArrayList<>())
                .planet(new Planet("Tatooine"))
                .build();

        when(swapiClient.getSwapiCharacterFromApi(name)).thenReturn(Optional.of(character));

        CharacterDto result = swapiService.getSwapiCharacter(name);
        assertEquals(character.getName(), result.getName());
    }
}
package com.jars.divergertestapp.application.service;

import com.jars.divergertestapp.application.dto.CharacterDto;
import com.jars.divergertestapp.application.dto.FilmDto;
import com.jars.divergertestapp.domain.model.Character;
import com.jars.divergertestapp.infrastructure.exceptionhandler.exceptions.NotCharacterFoundException;
import com.jars.divergertestapp.infrastructure.external.SwapiClient;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SwapiService {

    @Autowired
    private SwapiClient swapiClient;

    @Autowired
    private ModelMapper modelMapper;

    @Cacheable(value = "swapiCharacters", key = "#name")
    public CharacterDto getSwapiCharacter(String name) {
        Optional<Character> character = swapiClient.getSwapiCharacterFromApi(name);
        List<FilmDto> filmsDto = List.of();
        if(character.isEmpty()) {
            throw new NotCharacterFoundException();
        }
        if(character.get().getFilmsList() != null) {
             filmsDto = character.get().getFilmsList().stream()
                    .map(film -> modelMapper.map(film, FilmDto.class))
                    .toList();
        }

        return CharacterDto.builder()
                .name(character.get().getName())
                .birthYear(character.get().getBirthYear())
                .gender(character.get().getGender())
                .planetName(character.get().getPlanet().getName())
                .fastestVehicleDriven(character.get().getFastestVehicleDriven())
                .films(filmsDto)
                .build();
    }
}

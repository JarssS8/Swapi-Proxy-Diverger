package com.jars.divergertestapp.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CharacterDto {
    @JsonProperty("name")
    private String name;
    @JsonProperty("birth_year")
    private String birthYear;
    @JsonProperty("gender")
    private String gender;
    @JsonProperty("planet_name")
    private String planetName;
    @JsonProperty("fastest_vehicle_driven")
    private String fastestVehicleDriven;
    @JsonProperty("films")
    private List<FilmDto> films;
}

package com.jars.divergertestapp.domain.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class Character {
    private String name;
    private String birth_year;
    private String gender;
    private Planet homeWorld;
    private List<Film> films;
    private List<StarshipVehicle> starships;
    private List<TerrainVehicle> vehicles;
}

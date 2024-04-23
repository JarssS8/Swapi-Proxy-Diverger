package com.jars.divergertestapp.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Character {
    @JsonProperty("name")
    private String name;
    @JsonProperty("birth_year")
    private String birthYear;
    @JsonProperty("gender")
    private String gender;
    @JsonProperty("homeworld")
    private String homeWorldUrl;
    private Planet planet;
    @JsonProperty("films")
    private List<String> films;
    @JsonProperty("starships")
    private List<String> starships;
    @JsonProperty("vehicles")
    private List<String> vehicles;
    List<Film> filmsList;
    List<StarshipVehicle> starshipsList;
    List<TerrainVehicle> vehiclesList;

    public String getFastestVehicleDriven() {
        if (this.starshipsList == null && this.vehiclesList == null) {
            return "No vehicle driven";
        }

        Stream<VehicleBase> vehicleStream = Stream.empty();

        if (this.starshipsList != null) {
            vehicleStream = Stream.concat(vehicleStream, this.starshipsList.stream());
        }

        if (this.vehiclesList != null) {
            vehicleStream = Stream.concat(vehicleStream, this.vehiclesList.stream());
        }

        return vehicleStream
                .filter(vehicle -> vehicle.getMaxAtmosphericSpeed() != null)
                .max(Comparator.comparingInt(VehicleBase::getMaxAtmosphericSpeed))
                .map(VehicleBase::getName)
                .orElse("No vehicle driven");
    }
}

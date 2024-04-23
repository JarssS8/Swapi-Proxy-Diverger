package com.jars.divergertestapp.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FilmDto {
    @JsonProperty("name")
    private String name;
    @JsonProperty("release_date")
    private String releaseDate;
}

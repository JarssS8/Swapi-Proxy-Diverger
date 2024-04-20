package com.jars.divergertestapp.domain.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class Film {
    private String name;
    private Date relaseDate;
}

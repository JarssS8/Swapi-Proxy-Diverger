package com.jars.divergertestapp.infrastructure.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Validated
@Data
@AllArgsConstructor
public class ErrorItem {
    @JsonProperty("title")
    @Size(max = 100)
    private String title;

    @JsonProperty("code")
    @Size(max = 10)
    private String code;

    @JsonProperty("description")
    @Size(max = 100)
    private String description;
}

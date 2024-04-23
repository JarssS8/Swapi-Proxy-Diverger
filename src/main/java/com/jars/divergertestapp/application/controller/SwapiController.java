package com.jars.divergertestapp.application.controller;

import com.jars.divergertestapp.application.dto.CharacterDto;
import com.jars.divergertestapp.application.service.SwapiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/swapi-proxy")
@Tag(name = "Swapi Proxy", description = "API proxy for the Star Wars API (Swapi)")
public class SwapiController {

    @Autowired
    private SwapiService swapiService;

    @GetMapping("/person-info")
    @Operation(summary = "Get character", description = "Get character information from the Star Wars API (Swapi)")
    public ResponseEntity<CharacterDto> getPersonInfo(@RequestParam String name) {
        return ok(swapiService.getSwapiCharacter(name));
    }
}

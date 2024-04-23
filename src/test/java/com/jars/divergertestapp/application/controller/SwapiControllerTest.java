package com.jars.divergertestapp.application.controller;

import com.jars.divergertestapp.application.service.SwapiService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SwapiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetCharacterCorrect() throws Exception {
        String name = "Luke";

        mockMvc.perform(get("/swapi-proxy/person-info?name=" + name))
                .andExpect(status().isOk());
    }
}

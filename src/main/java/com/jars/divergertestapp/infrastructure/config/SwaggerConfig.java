package com.jars.divergertestapp.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI defineOpenApi() {
        Server server = new Server();
        server.setUrl("localhost:8080");
        server.setDescription("Swapi Proxy Server");

        Contact myContact = new Contact();
        myContact.setName("JarssS8");
        myContact.setEmail("adraincgs@gmail.com");

        Info information = new Info()
                .title("Swapi Proxy API")
                .version("1.0")
                .description("This is a simple API proxy for the Star Wars API (Swapi)")
                .contact(myContact);
        return new OpenAPI().info(information).servers(List.of(server));
    }
}

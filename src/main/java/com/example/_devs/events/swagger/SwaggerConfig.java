package com.example._devs.events.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI setup() {
        return new OpenAPI()
                .info(new Info()
                        .title("Gerenciador de eventos")
                        .description("API para gerenciamento de eventos")
                        .contact(
                                new Contact().email("fernelladev@gmail.com.br").name("fernelladev")
                        )
                        .version("1.0.0"));
    }
}

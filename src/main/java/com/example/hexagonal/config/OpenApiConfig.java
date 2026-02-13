package com.example.hexagonal.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Hexagonal Architecture API")
                        .version("1.0.0")
                        .description("API REST con arquitectura hexagonal para gestión de Clientes y Órdenes")
                        .contact(new Contact()
                                .name("API Support")
                                .url("http://localhost:8080")));
    }
}

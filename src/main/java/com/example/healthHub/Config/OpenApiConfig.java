package com.example.healthHub.Config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;

public class OpenApiConfig {

    @Bean
    public OpenAPI baseOpenApi(){
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("SpringBoot OpenApi Docs")
                        .version("1.0.0").description("Doc Description"));
    }
}

package com.saber.spring_boot_web_demo.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI openAPI(@Value(value = "${service.swagger.title}") String swaggerTitle,
                           @Value(value = "${service.swagger.description}") String swaggerDescription,
                           @Value(value = "${service.swagger.version}") String swaggerVersion){
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title(swaggerTitle)
                        .version(swaggerVersion)
                        .description(swaggerDescription)
                );

    }
}
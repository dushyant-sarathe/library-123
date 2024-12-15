package com.libraray.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@OpenAPIDefinition
public class SwaggerConfig {

    @Value("${server.port}")
    private String port;

    @Bean
    OpenAPI apiInfo() {
        final String securitySchemeName = "bearerAuth";

        // List of servers
//        List<Server> servers = new ArrayList<>();
//        servers.add(new Server().url("https://elevateb.claimtec.co.za").description("Secured Production server url"));
//        servers.add(new Server().url("http://localhost:" + port).description("Localhost server url"));

        // Build the OpenAPI instance with servers and security
        return new OpenAPI()
               // .servers(servers)
                .addSecurityItem(new SecurityRequirement()
                        .addList(securitySchemeName))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName,
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                                        .in(SecurityScheme.In.HEADER)
                                        .name("Bearer Authentication")))
                .info(new Info().title("lIBRARY-MANAGEMENT-RESTAPI")
                        .description("A Spring Boot-based REST API for Library management.")
                        .version("v0.0.1")
                        .license(new License().name("MIT").url("https://opensource.org/license/mit/")))
                .externalDocs(new ExternalDocumentation()
                        .description("GitHub Repository")
                        .url("https://github.com/akhmsoft/elevate_backend"));
    }
}
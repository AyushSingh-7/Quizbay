package com.example.generalService.configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {
    @Bean
    public GroupedOpenApi adminApi() {
        return GroupedOpenApi.builder()
                .group("my-test-app")
                .pathsToMatch("/admin/**")
                .build();
    }
    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Performance Test API")
                        .description("Phani Sample APP")
                        .version("v0.0.1")
                        .license(new License().name("Restricted to training purposes").url("http://test.test.com")))
                .externalDocs(new ExternalDocumentation()
                        .description("No document as of now")
                        .url("nothing as of now"));
    }
}




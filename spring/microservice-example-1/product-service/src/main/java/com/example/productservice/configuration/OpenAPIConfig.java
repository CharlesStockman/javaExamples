package com.example.productservice.configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI productServiceAPI() {
        return new OpenAPI()
                .info(new Info()
                    .title("Product Service API")
                    .description("Rest API for product")
                    .version("v0.0.1")
                    .license(new License().name("Apache 2.0")))
                .externalDocs(new ExternalDocumentation()
                        .description("Summaries of the components used in this microservice")
                        .url("http://www.yahoo.com"));
    }
}

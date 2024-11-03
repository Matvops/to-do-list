package com.cadenassi.to_do_list.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Matheus
 */

@Configuration
public class OpenApiConfig{

    @Bean
    OpenAPI customApiConfig() {
        return new OpenAPI()
                .info(new Info()
                        .title("TO DO LIST PROJECT")
                        .description("Project created using RESTFUL API, JAVA, SPRING BOOT, " +
                                "MYSQL, SWAGGER, FLYWAY AND MVC PATTERN")
                        .summary("PROJECT TO DO LIST")
                        .version("v1")
                        .termsOfService("https://github.com/Matvops/to-do-list")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://github.com/Matvops/to-do-list")));
    }
}

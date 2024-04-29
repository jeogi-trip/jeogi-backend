package com.jeogi.jeogitrip.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title("저기어때 API")
                .description(
                        "jeogitrip API")
                .version("v1");
        return new OpenAPI().components(new Components()).info(info);
    }

    @Bean
    public GroupedOpenApi boardApi(){
        return GroupedOpenApi.builder().group("jeogi-board").pathsToMatch("/api/**").build();
    }

    @Bean
    public GroupedOpenApi attractionApi(){
        return GroupedOpenApi.builder().group("jeogi-attraction").pathsToMatch("/api/**").build();
    }

    @Bean
    public GroupedOpenApi userApi(){
        return GroupedOpenApi.builder().group("jeogi-user").pathsToMatch("/api/**").build();
    }
}

package com.example.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerAPI {

    @Bean
    public OpenAPI customOpenAPI(){

        return  new OpenAPI().info(new Info()
                .title("Student-Teacher ManagementAPI")
                        .version("1.0")
                .description("REST API for Managing Teacher and Students")
        )
                .servers(List.of(new Server().url("http://localhost:8080/api").description("local"),
                                new Server().url("http://localhost:8081/api").description("Prod"))
                        );

    }

}

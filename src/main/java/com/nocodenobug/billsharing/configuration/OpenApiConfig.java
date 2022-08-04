package com.nocodenobug.billsharing.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

  @Bean
  public OpenAPI openApi() {
    return new OpenAPI()
        .info(
            new Info()
                .title("Application to share bill")
                .description("List of APIs")
                .version("v1.0")
                .contact(
                    new Contact()
                        .name("NoCodeNoBug")
                        .url("https://ghtk.me/")
                        .email("tamdd18@ghtk.co")));
  }
}

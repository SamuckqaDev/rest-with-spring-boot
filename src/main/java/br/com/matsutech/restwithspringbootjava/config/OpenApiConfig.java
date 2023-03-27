package br.com.matsutech.restwithspringbootjava.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI customOpenApi() {
		return new OpenAPI()
				.info(new Info()
						.title("Project REST created with Spring Boot")
						.version("v1")
						.description("API with Spring Boot")
						.termsOfService("www.termsofservice.matsutech")
						.license(new License()
								.name("Apache 2.0")
								.url("https://github.com/samuckqadev")));
	}
	
}

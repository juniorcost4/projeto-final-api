package br.org.serratec.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("br.org.serratec.backend.controller"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo());
	}
	
	public ApiInfo apiInfo() {
		ApiInfo apiInfo = new ApiInfoBuilder()
				.title("API Final Serratec")
				.description("Essa e uma API de desenvolvida durante o Serratec/2021.2")
				.license("Apache License version 2.0")
				.licenseUrl("https://www.apache.org/license")
				.version("1.0.0")
				.contact(new Contact("Serratec", "www.serratec.org.br", "serratec@gmail.com"))
				.build();
		return apiInfo;
	}
}

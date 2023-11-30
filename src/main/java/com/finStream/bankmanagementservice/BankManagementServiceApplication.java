package com.finStream.bankmanagementservice;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@OpenAPIDefinition(
		info = @Info(
				title = "Bank Management Microservice REST API Documentation",
				description = "Fin Stream Bank Management Service REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Abi Anand",
						email = "abianand382@gmail.com",
						url = "http://none"
				),
				license = @License(
						name = "Apache License, Version 2.0"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "None",
				url = "none"
		)

)
public class BankManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankManagementServiceApplication.class, args);
	}

}

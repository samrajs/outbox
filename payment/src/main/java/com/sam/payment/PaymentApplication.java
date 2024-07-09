package com.sam.payment;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.sam.payment"})
@OpenAPIDefinition(
		info = @Info(
				title = "Microservice for payment management REST API documentation",
				description = "It consumes message from order topic and process payment for that order",
				version = "v1",
				contact = @Contact(
						name = "Samraj Subramaniam",
						email = "samrajs@gmail.com",
						url = "https://www.sam-gmbh.de"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.sam-gmbh.de"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "It consumes message from order topic and process payment for that order",
				url = "https://www.sam-gmbh.de"
		)
)
public class PaymentApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentApplication.class, args);
	}

}

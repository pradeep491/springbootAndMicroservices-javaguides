package com.test.employeeservice;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@OpenAPIDefinition(
        info = @Info(
                title = "Employee Service Rest API's",
                description = "Employee Service Rest API's Documentation",
                version = "V1.0",
                contact = @Contact(
                        name = "KANDYALA PRADEEP KUMAR",
                        email = "K.PRADEEP0491@GMAIL.COM",
                        url = "https://www.google.com"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.google.com"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "Employee Service.doc",
                url = "https://www.google.com"
        )
)
@SpringBootApplication
//@EnableFeignClients
public class EmployeeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeServiceApplication.class, args);
    }
    @Bean
    public WebClient webClient() {
        return WebClient.builder().build();
    }
}

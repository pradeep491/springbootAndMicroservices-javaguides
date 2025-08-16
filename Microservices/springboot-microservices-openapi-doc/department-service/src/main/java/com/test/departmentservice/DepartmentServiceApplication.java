package com.test.departmentservice;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.models.annotations.OpenAPI30;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
        info = @Info(
                title = "Department Service Rest API's",
                description = "Department Service Rest API's Documentation",
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
                description = "Department Service.doc",
                url = "https://www.google.com"
        )
)
@SpringBootApplication
public class DepartmentServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DepartmentServiceApplication.class, args);
    }

}

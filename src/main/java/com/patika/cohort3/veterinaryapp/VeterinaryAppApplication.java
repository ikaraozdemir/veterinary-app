package com.patika.cohort3.veterinaryapp;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Veterinary App", version = "1.0", description = "Veterinary App"))
public class VeterinaryAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(VeterinaryAppApplication.class, args);
    }

}

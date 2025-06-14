package com.prueba.loginAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.prueba.loginAPI.infrastructure.entities.*") // <-- Add this
public class loginAPIApplication {

	public static void main(String[] args) {
		SpringApplication.run(loginAPIApplication.class, args);
	}

}

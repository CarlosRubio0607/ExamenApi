package com.api.examenJava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.api.examenJava", "com.api.controller"})
public class ExamenJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExamenJavaApplication.class, args);
	}

}

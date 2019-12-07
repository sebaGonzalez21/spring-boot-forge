package com.forge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.forge") // le dice a Spring en qué paquetes ha anotado clases que Spring debe administrar
public class ForgeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForgeApplication.class, args);
	}

}

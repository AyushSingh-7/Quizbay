package com.example.generalService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GeneralServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeneralServiceApplication.class, args);
	}

}

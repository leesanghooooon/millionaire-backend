package com.home.millionairebackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MillionaireBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MillionaireBackendApplication.class, args);
	}

}

package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SpringBootApplication
public class BootApp {
	
	static final Logger logger = LogManager.getLogger(BootApp.class.getName());

	public static void main(String[] args) {
		SpringApplication.run(BootApp.class, args);
		logger.info("APLICACION INICIADA!!! :p");
	}
}

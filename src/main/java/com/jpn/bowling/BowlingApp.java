package com.jpn.bowling;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BowlingApp {
	private static final Logger LOGGER = LogManager.getLogger(BowlingApp.class);
	
	public static void main(String[] args) {
		SpringApplication.run(BowlingApp.class, args);
	}
}

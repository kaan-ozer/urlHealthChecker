package com.example.urlHealthChecker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class UrlHealthCheckerApplication {

	public static void main(String[] args) {
		SpringApplication.run(UrlHealthCheckerApplication.class, args);
	}

}

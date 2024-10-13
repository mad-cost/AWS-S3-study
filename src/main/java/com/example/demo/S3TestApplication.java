package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class S3TestApplication {
	public static void main(String[] args) {
		SpringApplication.run(S3TestApplication.class, args);
	}
}

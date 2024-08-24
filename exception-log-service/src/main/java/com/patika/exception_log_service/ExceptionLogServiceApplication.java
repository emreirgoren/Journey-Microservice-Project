package com.patika.exception_log_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class ExceptionLogServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExceptionLogServiceApplication.class, args);
	}

}

package com.patika.ticket_search_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class TicketSearchServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketSearchServiceApplication.class, args);
	}

}

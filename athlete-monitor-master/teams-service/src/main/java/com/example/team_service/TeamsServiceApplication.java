package com.example.team_service;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients

public class TeamsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeamsServiceApplication.class, args);
	}

}

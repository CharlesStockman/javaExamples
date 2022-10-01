package com.student.microservice.finesService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableEurekaClient
public class FinesServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinesServiceApplication.class, args);
	}

}

package com.microservices.composite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CustomerCompositeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerCompositeApplication.class, args);
    }
}

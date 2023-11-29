package com.finStream.bankmanagementservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BankManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankManagementServiceApplication.class, args);
	}

}

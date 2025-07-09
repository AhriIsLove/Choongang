package com.oracle.oBootMSAUserService01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OBootMsaUserService01Application {

	public static void main(String[] args) {
		SpringApplication.run(OBootMsaUserService01Application.class, args);
	}

}

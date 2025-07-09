package com.oracle.oBootMSADiscoveryService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class OBootMsaDiscoveryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OBootMsaDiscoveryServiceApplication.class, args);
	}

}

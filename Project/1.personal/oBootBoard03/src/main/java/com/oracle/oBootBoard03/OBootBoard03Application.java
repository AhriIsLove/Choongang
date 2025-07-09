package com.oracle.oBootBoard03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing     // JPA Auditing 활성화
public class OBootBoard03Application {

	public static void main(String[] args) {
		SpringApplication.run(OBootBoard03Application.class, args);
	}

}

package com.oracle.oBootMSAUserService01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oracle.oBootMSAUserService01.vo.Dept;
import com.oracle.oBootMSAUserService01.vo.Greeting;

@RestController
@RequestMapping("/")
public class UserServiceController01 {
	private Environment environment;
	
	public UserServiceController01(Environment environment) {
		this.environment = environment;
	}
	
	@Autowired
	private Greeting greeting;
	
	@GetMapping("/health_check")
	public String health_check() {
		System.out.println("health_check Start...");
		return "health_check On";
	}

	@GetMapping("/greetingMessage")
	public String greetingMessage() {
		System.out.println("greetingMessage Start...");
		return greeting.getMessage();
	}
	
	@GetMapping("/deptMessage")
	public Dept deptMessage(Dept dept) {
		System.out.println("greeting Message Start...");
		dept.setDeptno(1234);
		dept.setDname(greeting.getMessage());
		dept.setLoc("홍대");
		
		return dept;
	}
}

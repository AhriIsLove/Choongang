package com.oracle.oBootSeqcurity01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	@GetMapping(value = "/")
	public String home() {
		System.out.println("HomeController home");
		
		return "main";
	}
	@GetMapping(value = "/user")
	public String user() {
		System.out.println("HomeController user");
		
		return "user";
	}
	@GetMapping(value = "/manager")
	public String manager() {
		System.out.println("HomeController manager");
		
		return "manager";
	}
	@GetMapping(value = "/admin")
	public String admin() {
		System.out.println("HomeController admin");
		
		return "admin";
	}
}

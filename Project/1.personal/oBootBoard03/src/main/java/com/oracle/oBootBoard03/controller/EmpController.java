package com.oracle.oBootBoard03.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/emp")
public class EmpController {
	@GetMapping("/list")
	public String mainPage() {
		System.out.println("emp/list Strart...");
		return "emp/list";
	}
}

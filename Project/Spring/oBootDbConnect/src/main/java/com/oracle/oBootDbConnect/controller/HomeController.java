package com.oracle.oBootDbConnect.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class HomeController {
	// 컨트롤러가 정적 파일보다 우선순위가 높다 : index.html로 가지 않는다.
	@GetMapping("/")
	public String home() {
		System.out.println("HomeController home Start...");
		return "home";
	}
	
}

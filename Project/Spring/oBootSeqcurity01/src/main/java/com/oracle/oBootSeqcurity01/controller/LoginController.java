package com.oracle.oBootSeqcurity01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@GetMapping("/signup")
	public String signup() {
		return "login/signup";
	}

	@GetMapping(value = "/denied")
	public String denied() {

		return "login/denied";
	}

	@GetMapping(value = "/loginPage")
	public String loginPage() {

		return "login/loginPagemin";
	}
}

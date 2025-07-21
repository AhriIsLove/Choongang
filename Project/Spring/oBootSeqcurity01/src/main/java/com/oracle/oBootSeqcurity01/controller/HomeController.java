package com.oracle.oBootSeqcurity01.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
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

	@GetMapping(value = "/admanager")
	public String admanager() {
		System.out.println("HomeController admanager");

		return "admanager";
	}

	// @Secured("ROLE_GUEST") : guest만 접근 가능한 uri
	@Secured("ROLE_GUEST")
	@GetMapping(value = "/guest")
	public String guest() {
		System.out.println("HomeController guest");

		return "guest";
	}

	// @Secured({"", "", ""}) : 지정된 권한을 가진 계정만 접근 가능한 uri
	@Secured({ "ROLE_GUEST", "ROLE_ADMIN", "ROLE_MANAGER" })
	@GetMapping(value = "/guest3")
	public String guest3() {
		System.out.println("HomeController guest3");

		return "guest";
	}

	// @PreAuthorize() : 지정된 권한을 가진 계정만 접근 가능한 uri
	@PreAuthorize("hasRole('USER') or hasRole('MANAGER')")
	@GetMapping(value = "/guest5")
	public String guest5() {
		System.out.println("HomeController guest5");

		return "guest";
	}
}

package com.oracle.oBootSeqcurity01.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.oracle.oBootSeqcurity01.model.AccountDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

	@GetMapping(value = "/login")
	public String login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "exception", required = false) String exception, Model model) {
		System.out.println("LoginController login");

		model.addAttribute("error", error);
		model.addAttribute("exception", exception);

		return "login/loginPage";
	}

	@GetMapping("/signup")
	public String signup() {
		return "login/signup";
	}

	@GetMapping(value = "/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("LoginController logout");

		Authentication authentication = SecurityContextHolder.getContextHolderStrategy().getContext()
				.getAuthentication();

		if (authentication != null) {
			new SecurityContextLogoutHandler().logout(request, response, authentication);
		}

		return "redirect:/login";
	}

	@GetMapping(value = "/denied")
	public String denied(@RequestParam(value = "exception", required = false) String exception,
			@AuthenticationPrincipal AccountDto accountDto, Model model) {

		model.addAttribute("username", accountDto.getUsername());
		model.addAttribute("exception", exception);

		return "login/denied";
	}

}

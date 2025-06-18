package com.oracle.oBootMybatis01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.oracle.oBootMybatis01.service.MemberJpaService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberJpaController {
	private final MemberJpaService memberJpaService;
	
	@GetMapping("/memberJpa/new")
	public String createFrom() {
		System.out.println("memberJpa 1");
		
		return "memberJpa/createMemberForm";
	}
	
}

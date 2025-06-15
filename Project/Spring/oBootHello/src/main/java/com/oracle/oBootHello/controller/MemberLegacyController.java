package com.oracle.oBootHello.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.oracle.oBootHello.dto.Member1;
import com.oracle.oBootHello.service.MemberLegacyService;

@Controller
public class MemberLegacyController {
	private static final Logger LOGGER = LoggerFactory.getLogger(MemberLegacyController.class);
	// 1. 전통적(Legacy) 방식:Controller
	MemberLegacyService memberLegacyService = new MemberLegacyService();
	
	@GetMapping(value = "memberLsave")
	public String save(Member1 member1) {
		System.out.println("MemberLegacyController save start...");
		System.out.println("MemberLegacyController save member1 : " + member1);
		Long id = memberLegacyService.memberSave(member1);
		
		//빈 경로로 찾아가라 : index.html을 찾아가라
		return "redirect:/";
	}
}

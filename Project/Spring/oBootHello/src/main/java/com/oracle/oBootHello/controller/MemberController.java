package com.oracle.oBootHello.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.oracle.oBootHello.dto.Member1;
import com.oracle.oBootHello.service.MemberService;

@Controller
public class MemberController {
	private static final Logger LOGGER = LoggerFactory.getLogger(MemberController.class);

	// 2. SpringBoot 방식
	// Service 연결
	private final MemberService memberService;
	// 2-1. DI(의존성 부여) : 생성자로 부여 
	@Autowired//DI-Constructor Injection
	public MemberController(MemberService memberService) {
		// TODO Auto-generated constructor stub
		this.memberService = memberService;
	}
	
	@GetMapping(value="members/memberForm")
	public String memberForm(){
		System.out.println("MemberController /members/memberForm Start...");
		
		return "members/memberForm";
	}

	// value=는 안쓰는게 보통
	@PostMapping(value="members/save")
	// @RequestParam을 사용하지 않는 이유 : 객체를 파라미터로 사용하는 경우 안써도 된다. 
	private String save(Member1 member1) {
		System.out.println("MemberController /members/save Start...");
		System.out.println("MemberController /members/save member1 : " + member1);
		Long id = memberService.memberSave(member1);
		System.out.println("MemberController /members/save id : " + id);

		//빈 경로로 찾아가라 : static/index.html을 찾아가라
		return "redirect:/";
	}
	
	@GetMapping(value="members/memberList")
	public String memberList(Model model) {
		LOGGER.info("memberList Start...");
		List<Member1> memberLists = memberService.allMembers();
		model.addAttribute("memberLists", memberLists);
		LOGGER.info("memberLists.size() : {}", memberLists.size());
		
		return "members/memberList";
	}
}

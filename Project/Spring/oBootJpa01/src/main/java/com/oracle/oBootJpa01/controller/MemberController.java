package com.oracle.oBootJpa01.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oracle.oBootJpa01.domain.Member;
import com.oracle.oBootJpa01.service.MemberService;

@Controller
public class MemberController {
	private static final Logger LOGGER = LoggerFactory.getLogger(MemberController.class);
	
	private final MemberService memberService;
	@Autowired
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@GetMapping("/members/new")
	public String createForm() {
		System.out.println("MemberController /members/new start..");
		
		return "/members/createMemberForm";
	}
	
	@PostMapping("/members/save")
	public String memberSave(Member member) {
		System.out.println("MemberController memberSave start...");
		System.out.println("MemberController member : " + member);
		System.out.println("MemberController member.getId() : " + member.getId());
		System.out.println("MemberController member.getName() : " + member.getName());
		Long id = memberService.memberSave(member);
		System.out.println("MemberController memberSave end id : " + id);
		
		return "redirect:/";
	}
	
	@GetMapping("/members")
	public String listmember(Model model) {
		List<Member> memberList = memberService.getListAllMember();
		LOGGER.info("memberList.size() : {}.", memberList.size());
		model.addAttribute("members", memberList);
		
		return "/members/memberList";
	}
	
	@PostMapping("members/search")
	public String membersSearch(Member member, Model model) {
		System.out.println("members/search member.getName() : " + member.getName());
		List<Member> memberList = memberService.getListSearchMember(member.getName());
		LOGGER.info("memberList.size() : {}.", memberList.size());
		model.addAttribute("members", memberList);
		
		return "/members/memberList";
	}
}

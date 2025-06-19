package com.oracle.oBootMybatis01.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oracle.oBootMybatis01.domain.Member;
import com.oracle.oBootMybatis01.service.MemberJpaService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
@Slf4j
//@Mapping 기본 경로 설정
@RequestMapping("/memberJpa")
public class MemberJpaController {
	private final MemberJpaService memberJpaService;
	
	@GetMapping("/new")
	public String createFrom() {
		System.out.println("new 1");
		
		return "memberJpa/createMemberForm";
	}
	
	@PostMapping("/save")
	public String create(Member member) {
		System.out.println("create 1 member : " + member);
		
		memberJpaService.join(member);

		return "memberJpa/createMemberForm";
	}
	
	@GetMapping("/members")
	public String listMember(Model model) {
		System.out.println("listMember ( •̀ ω •́ )✧ 1");
		List<Member> memberList = memberJpaService.getListAllMember();
		model.addAttribute("members", memberList);
		
		return "memberJpa/memberList";
	}
	
	@GetMapping("/memberUpdateForm")
	public String memberUpdateForm(Member pMember, Model model) {
		String returnJsp = "";
		Member member = null;
		System.out.println("memberUpdateForm (～￣▽￣)～ S ID : " + pMember.getId());
		
		Optional<Member> maybeMember = memberJpaService.findById(pMember.getId());
		
		//maybeMember이 존재한다면
		if(maybeMember.isPresent()) {
			System.out.println("maybeMember IS NOT NULL");
			member = maybeMember.get();
			model.addAttribute("member", member);
			
			returnJsp = "memberJpa/memberModify";
		}
		else {
			System.out.println("maybeMember IS NULL");
			model.addAttribute("message", "member가 존재하지 않으니, 입력부터 수행해주세요");
			
			returnJsp = "forward:/memberJpa/members";
		}

		System.out.println("memberUpdateForm (～￣▽￣)～ E ID : " + pMember.getId());
		
		return returnJsp;
	}
	
	@GetMapping("/memberUpdate")
	public String memberUpdate(Member member, Model model) {
		System.out.println("memberUpdate <(＿　＿)> S member : " + member);
		
		memberJpaService.memberUpdate(member);
		
		System.out.println("memberUpdate <(＿　＿)> E");
		
		//이것도 해줘야 하네... @RequestMapping("/memberJpa") 쓰지 맙시다 불편하네...
		return "redirect:/memberJpa/members";
	}
}

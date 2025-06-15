package com.oracle.oBootJpaApi01.controller;

import org.springframework.web.bind.annotation.RestController;

import com.oracle.oBootJpaApi01.domain.Member;
import com.oracle.oBootJpaApi01.service.MemberService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


//RestAPI용 Controller 설정
@RestController
//-@Controller
//-@ResponseBody
//LOGGER 설정
@Slf4j
@RequiredArgsConstructor
public class JpaRestApiController {
	private final MemberService memberService;
	
	@RequestMapping("/helloText")
	public String helloText() {
		System.out.println("JpaRestApiController helloText Start...");
		String hello = "안녕";
//		@Slf4j : LOGGER
		log.info("log.info : {}.", hello);
		
		return hello;
	}
	
	// 모든 데이터 전송
	@GetMapping("/restApi/v1/members")
	public List<Member> membersVer1(){
		System.out.println("JpaRestApiController /restApi/v1/members Start...");
		List<Member> listMember = memberService.getListAllMember();
		System.out.println("JpaRestApiController /restApi/v1/members listMember.size() : " + listMember.size());
		
		return listMember;
	}
	
	// 일부 데이터 및 가공 데이터 전송
	// 일부 데이터를 가지는 반환용 Dto 클래스 생성 후 활용
	@GetMapping("/restApi/v21/members")
	public Result membersVer21(){
		List<Member> findMembers = memberService.getListAllMember();
		System.out.println("JpaRestApiController /restApi/v21/members listMember.size() : " + findMembers.size());
		List<MemberRtnDto> resultList = new ArrayList<MemberRtnDto>();
		
		for(Member member : findMembers) {
			MemberRtnDto memberRtnDto = new MemberRtnDto(member.getName(), member.getSal());
			System.out.println("JpaRestApiController /restApi/v21/members getName : " + memberRtnDto.getName());
			System.out.println("JpaRestApiController /restApi/v21/members getSal : " + memberRtnDto.getSal());
			resultList.add(memberRtnDto);
		}
		System.out.println("JpaRestApiController /restApi/v21/members resultList.size() : " + resultList.size());
		
		return new Result(resultList.size(), resultList);
	}

	// 일부 데이터 및 가공 데이터 전송
	// Lambda(람다) 방식
	@GetMapping("/restApi/v22/members")
	public Result membersVer22(){
		List<Member> findMembers = memberService.getListAllMember();
		System.out.println("JpaRestApiController /restApi/v22/members listMember.size() : " + findMembers.size());
		List<MemberRtnDto> memberCollect = 
				findMembers.stream()
				.map(member->new MemberRtnDto(member.getName(), member.getSal()))
				.collect(Collectors.toList());
		System.out.println("JpaRestApiController /restApi/v22/members memberCollect.size() : " + memberCollect.size());
		
		return new Result(memberCollect.size(), memberCollect);
	}
	
	@Data
	@AllArgsConstructor
	private class MemberRtnDto{
		private String name;
		private Long sal;
	}

	@Data
	@RequiredArgsConstructor
	private class Result<T>{
		private final int totCount;
//		private final Long totSal;
		private final T data;
	}
	
	@PostMapping("/restApi/v1/memberSave")
	public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member) { // @Valid : 값이 유효한지 확인
		System.out.println("JpaRestApiController /restApi/v1/memberSave member : " + member);
		log.info("member.getName() : {}", member.getName());
		log.info("member.getSal() : {}", member.getSal());
		
		Long id = memberService.saveMember(member);
		return new CreateMemberResponse(id);
	}
	
	@Data
//	@RequiredArgsConstructor //@Data에 포함되어 있음
	private class CreateMemberResponse{
		private final Long id;
	}
}

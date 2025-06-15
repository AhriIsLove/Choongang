package com.oracle.oBootHello.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.oBootHello.dto.Member1;
import com.oracle.oBootHello.repository.MemberRepository;

@Service
public class MemberService {
	// 2. SpringBoot 방식
	// repository 연결
	private final MemberRepository memberRepository;
	// 2-1. DI(의존성 부여) : 생성자로 부여 
	@Autowired
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	// 회원 가입
	public Long memberSave(Member1 member1) {
		System.out.println("MemberService memberSave start...");
		memberRepository.save(member1);
		return member1.getId();
	}
	
	// 전체 조회
	public List<Member1> allMembers() {
		System.out.println("MemberService allMembers start...");
		List<Member1> memList = memberRepository.findAll();
		System.out.println("MemberService memList.size() : " + memList.size());
		return memList;
	}

}

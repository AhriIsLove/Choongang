package com.oracle.oBootHello.service;

import com.oracle.oBootHello.dto.Member1;
import com.oracle.oBootHello.repository.MemberLegacyRepository;
import com.oracle.oBootHello.repository.MemberLegacyRepositoryImpl;

public class MemberLegacyService {
	// 1. 전통적(Legacy) 방식:Service
	// Interface = new implementsClass
	MemberLegacyRepository memberLegacyRepository = new MemberLegacyRepositoryImpl();
	//회원 가입
	public Long memberSave(Member1 member1) {
		System.out.println("MemberService memberSave start...");
		memberLegacyRepository.save(member1);
		
		return member1.getId();
	}
	//전체 조회
	public String allMembers() {
		System.out.println("MemberService allMembers start...");
		String findMsg = memberLegacyRepository.findAll_S();
		
		return findMsg;
	}
}

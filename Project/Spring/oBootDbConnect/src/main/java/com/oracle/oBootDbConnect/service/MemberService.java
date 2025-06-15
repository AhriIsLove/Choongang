package com.oracle.oBootDbConnect.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.oBootDbConnect.domain.Member7;
import com.oracle.oBootDbConnect.repository.JdbcMemberRepository;
import com.oracle.oBootDbConnect.repository.MemberRepository;

@Service
public class MemberService {
	private final MemberRepository memberRepository;

	@Autowired
	public MemberService(MemberRepository memberRepository) {
		// TODO Auto-generated constructor stub
		this.memberRepository = memberRepository;
	}
	
	// 회원 가입
	public Long memberSave(Member7 member7) {
		memberRepository.save(member7);
		System.out.println("MemberService memberSave End...");
		return member7.getId();
	}
	
	// 전체회원 조회
	public List<Member7> findMembers() {
		System.out.println("MemberService findMembers Start...");
		return memberRepository.findAll();
	}
}

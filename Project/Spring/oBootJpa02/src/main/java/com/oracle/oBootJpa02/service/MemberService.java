package com.oracle.oBootJpa02.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.oBootJpa02.domain.Member;
import com.oracle.oBootJpa02.repository.MemberRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MemberService {
	private final MemberRepository memberRepository;
	@Autowired
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	public Member memberSave(Member member) {
		System.out.println("MemberService SAVE member : " + member);
		memberRepository.memberSave(member);
		System.out.println("MemberService RETURN member : " + member);

		return member;
	}

	public List<Member> getListAllMember() {
		List<Member> listMember = memberRepository.findAll();
		System.out.println("MemberService getListAllMember listMember.size() : " + listMember.size());
		
		return listMember;
	}

	public List<Member> getListSearchMember(String searchName) {
		System.out.println("MemberService getListSearchMember Start...");
		System.out.println("MemberService getListSearchMember searchName : " + searchName);
		List<Member> listMember = memberRepository.findByNames(searchName);
		System.out.println("MemberService getListSearchMember listMember.size() : " + listMember.size());
		
		return listMember;
	}

	public Member findByMember(BigDecimal memberId) {
		Member member = memberRepository.findByMember(memberId);
		System.out.println("MemberService findByMember member : " + member);
		
		return member;
	}

	public int memberUpdate(Member member) {
		System.out.println("MemberService memberUpdate member : " + member);
		int result = memberRepository.updateByMember(member);
		System.out.println("MemberService memberUpdate End..." + member);
		
		return result;
	}

	public List<Member> getListFindByMembers(Member member) {
		List<Member> listMember = memberRepository.findByMembers(member.getId(), member.getSal());
		System.out.println("MemberService getListFindByMembers listMember.size() : " + listMember.size());
		
		return listMember;
	}
}

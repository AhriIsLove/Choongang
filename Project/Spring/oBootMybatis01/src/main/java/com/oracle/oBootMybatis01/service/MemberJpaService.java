package com.oracle.oBootMybatis01.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.oracle.oBootMybatis01.domain.Member;
import com.oracle.oBootMybatis01.repository.MemberJpaRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberJpaService {
	private final MemberJpaRepository memberJpaRepository;
	
	public void join(Member member) {
		memberJpaRepository.save(member);
	}

	public List<Member> getListAllMember() {
		System.out.println("listMember ( •̀ ω •́ )✧ 2");
		List<Member> memberList = memberJpaRepository.findAll();
		System.out.println("listMember ( •̀ ω •́ )✧ 5 memberList.size() : " + memberList.size());
		
		return memberList;
	}

	public Optional<Member> findById(Long id) {
		System.out.println("memberUpdateForm (～￣▽￣)～ 1");
		
		Optional<Member> member = memberJpaRepository.findById(id);
		
		return member;
	}

	public void memberUpdate(Member member) {
		System.out.println("memberUpdate <(＿　＿)> 1");
		
		memberJpaRepository.updateByMember(member);
		
		return;
	}
}

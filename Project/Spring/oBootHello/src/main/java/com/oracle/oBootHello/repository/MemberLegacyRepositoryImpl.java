package com.oracle.oBootHello.repository;

import java.util.List;

import com.oracle.oBootHello.dto.Member1;

public class MemberLegacyRepositoryImpl implements MemberLegacyRepository {

	@Override
	public Member1 save(Member1 member1) {
		// TODO Auto-generated method stub
		System.out.println("MemberLegacyRepositoryImpl save...");
		return member1;
	}

	@Override
	public List<Member1> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String findAll_S() {
		// TODO Auto-generated method stub
		System.out.println("전체 조회");
		return "전체 조회";
	}
}

package com.oracle.oBootJpa01.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oracle.oBootJpa01.domain.Member;

import jakarta.persistence.EntityManager;

@Repository
public class JpaMemberRepository implements MemberRepository {
	// JPA DML --> EntityManager 필수  ***
	private final EntityManager entityManager;//DataSource 대신 사용
	@Autowired
	public JpaMemberRepository(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public Member memberSave(Member member) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Member> findAllMember() {
		// TODO Auto-generated method stub
		return null;
	}

}

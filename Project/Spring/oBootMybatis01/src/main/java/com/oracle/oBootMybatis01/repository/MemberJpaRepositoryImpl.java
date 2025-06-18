package com.oracle.oBootMybatis01.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.oracle.oBootMybatis01.domain.Member;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MemberJpaRepositoryImpl implements MemberJpaRepository {
	private final EntityManager em;
	
	@Override
	public Member save(Member member) {
		em.persist(member);
		
		return member;
	}

	@Override
	public List<Member> findAll() {
		List<Member> memberList = em.createQuery("SELECT m FROM Member m", Member.class).getResultList();
		
		return memberList;
	}

}

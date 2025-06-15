package com.oracle.oBootJpa01.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oracle.oBootJpa01.domain.Member;

import jakarta.persistence.EntityManager;

@Repository
public class JpaMemberRepository implements MemberRepository {
	// JPA DML --> EntityManager 필수 ***
	private final EntityManager entityManager;// DataSource 대신 사용

	@Autowired
	public JpaMemberRepository(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public Member memberSave(Member member) {
		// persist : INSERT
		// 실패시 Exception 발생 : 예외처리 필요
		entityManager.persist(member);

		return member;
	}

	@Override
	public List<Member> findAllMember() {
		// Entity명(객체명) 대소문자 구분 조심
		List<Member> memberList = entityManager.createQuery("SELECT m FROM Member m", Member.class).getResultList();
		System.out.println("JpaMemberRepository findAllMember memberList.size() : " + memberList.size());

		return memberList;
	}

	@Override
	public List<Member> findByNames(String searchName) {
		String pname = '%' + searchName + '%';
		System.out.println("JpaMemberRepository findByNames pname : " + pname);
		List<Member> memberList = 
				entityManager.createQuery("SELECT m FROM Member m WHERE name LIKE :name", Member.class)
				.setParameter("name", pname)
				.getResultList();
		System.out.println("JpaMemberRepository findByNames memberList.size() : " + memberList.size());

		return memberList;
	}

}

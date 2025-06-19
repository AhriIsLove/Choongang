package com.oracle.oBootMybatis01.repository;

import java.util.List;
import java.util.Optional;

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
		System.out.println("listMember ( •̀ ω •́ )✧ 3");
		List<Member> memberList = em.createQuery("SELECT m FROM Member m", Member.class).getResultList();
		System.out.println("listMember ( •̀ ω •́ )✧ 4 memberList.size() : " + memberList.size());
		
		return memberList;
	}

	@Override
	public Optional<Member> findById(Long id) {
		System.out.println("memberUpdateForm (～￣▽￣)～ 2");
		
		Member member = em.find(Member.class, id);
		
		return Optional.ofNullable(member);
	}

	@Override
	public void updateByMember(Member pMember) {
		System.out.println("updateByMember <(＿　＿)> 2");
		
		// UPDATE : merge 
		// - 현재 설정된 member의 값을 UPDATE
		// - 설정되지 않은 member값은 NULL, default
//		em.merge(pMember);
		
		// UPDATE : Setter
		// - Set한 값만 변경됨
		Member member = em.find(Member.class, pMember.getId());
		member.setName(pMember.getName());
	}
}

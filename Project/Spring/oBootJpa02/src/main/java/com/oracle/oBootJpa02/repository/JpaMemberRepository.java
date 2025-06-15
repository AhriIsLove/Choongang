package com.oracle.oBootJpa02.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oracle.oBootJpa02.domain.Member;
import com.oracle.oBootJpa02.domain.Team;

import jakarta.persistence.EntityManager;

@Repository
public class JpaMemberRepository implements MemberRepository {
	private final EntityManager em;

	@Autowired
	public JpaMemberRepository(EntityManager em) {
		this.em = em;
	}

	@Override
	public Member memberSave(Member member) {
		// 1.팀 저장
		Team team = new Team();
		team.setName(member.getTeamName());
		em.persist(team);

		// 2.회원 저장
		member.setTeam(team);
		em.persist(member);

		return member;
	}

	@Override
	public List<Member> findAll() {
		List<Member> memberList = em.createQuery("SELECT m FROM Member m", Member.class).getResultList();

		return memberList;
	}

	@Override
	public List<Member> findByNames(String searchName) {
		String pName = '%' + searchName + '%';
		List<Member> memberList = em.createQuery("SELECT m FROM Member m WHERE name LIKE :name", Member.class)
				.setParameter("name", pName).getResultList();

		return memberList;
	}

	@Override
	public Member findByMember(BigDecimal memberId) {
		// find : SELECT * FROM className WHERE pk = value;
		Member member = em.find(Member.class, memberId);

		return member;
	}

	@Override
	public int updateByMember(Member member) {
		int result = 0;
		System.out.println("JpaMemberRepository updateByMember member : " + member);
		Member findMember = em.find(Member.class, member.getId());
		// 존재하면 수정
		if (findMember != null) {
			System.out.println("JpaMemberRepository updateByMember member.getTeamId() : " + member.getTeamId());
			Team team = em.find(Team.class, member.getTeamId());
			if (team != null) {
				team.setName(member.getTeamName());
				em.persist(team);
			}
			// 회원 수정
			findMember.setTeam(team);
			findMember.setName(member.getName());
			// persist : INSERT or UPDATE
			em.persist(findMember);
			result = 1;
		} else {
			System.out.println("JpaMemberRepository updateByMember No Exist...");
		}

		return result;
	}

	
	@Override
	public List<Member> findByMembers(BigDecimal pId, Long pSal) {
		System.out.println("JpaMemberRepository findByMembers id : " + pId);
		System.out.println("JpaMemberRepository findByMembers sal : " + pSal);
		List<Member> memberList = em.createQuery("SELECT m FROM Member m WHERE id > :id and sal > :sal", Member.class)
				.setParameter("id", pId)
				.setParameter("sal", pSal)
				.getResultList();
		System.out.println("JpaMemberRepository findByMembers memberList.size() : " + memberList.size());
		
		return memberList;
	}

}

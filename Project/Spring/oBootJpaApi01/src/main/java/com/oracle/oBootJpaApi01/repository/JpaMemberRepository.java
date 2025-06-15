package com.oracle.oBootJpaApi01.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.oracle.oBootJpaApi01.domain.Member;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Repository
//final 변수 생성자 @Autowired
@RequiredArgsConstructor
public class JpaMemberRepository implements MemberRepository {
	private final EntityManager em;
	
	@Override
	public Long save(Member member) {
		System.out.println("JpaMemberRepository save...");
		em.persist(member);
		
		return member.getId();
	}

	@Override
	public List<Member> findAll() {
		// 여러 엔티티에서 가져오려면 어떻게 해야 하나
		// 안써도 된다고 함
		// Member.class 없어도 댐...
		List<Member> memberList = em.createQuery("SELECT m FROM Member m").getResultList();
//		List<Member> memberList = em.createQuery("SELECT m FROM Member m", Member.class).getResultList();
		System.out.println("JpaMemberRepository findAll memberList.size() : " + memberList.size());
		
		return memberList;
	}

	@Override
	public Member findByMember(Long memberId) {
		Member member = em.find(Member.class, memberId);
		
		return member;
	}

	@Override
	public Member findByBeforeMember(Long pid) {
		System.out.println("JpaMemberRepository findByBeforeMember before...");
		System.out.println("JpaMemberRepository findByBeforeMember pid->"+pid);
		Member member = null;
		
		//null 객체 저장 가능 하다고 합니다...
		Optional<Member> maybeMember = null;
		
		// Member member = em.find(Member.class, pid);
		try {
			maybeMember =  Optional.ofNullable(
					 em.createQuery("	select m from Member m"
								  + "	where m.id = (select max(m2.id) "
							 	  + "	              from   Member m2 "
								  + "	              where id < :pid) ", Member.class)
				       .setParameter("pid",pid)
				       .getSingleResult()
		     )
			 ;
			
		} catch (Exception e) {
			System.out.println("JpaMemberRepository findByBeforeMember maybeMember is null");

		}
		// 현재의 member id가 가장 적은 id(최소의 id)
		if (maybeMember != null ) {
			member = maybeMember.get();
		} else {
			member = findByMember(pid);
		}
	
  	    System.out.println("JpaMemberRepository findByBeforeMember member->"+member);

		return member;
	}
	
	@Override
	public int updateByMember(Member pMember) {
		int result = 0;
		
		Member member = em.find(Member.class, pMember.getId());
		if(member != null) {
			// 회원 수정
			member.setName(pMember.getName());
			member.setSal(pMember.getSal());
			result = 1;
			System.out.println("JpaMemberRepository updateByMember UPDATE...");
		}
		else {
			System.out.println("JpaMemberRepository updateByMember NO EXIST...");
		}
		
		return result;
	}

	@Override
	public int deleteById(Long id) {
		int result = 0;
		System.out.println("JpaMemberRepository deleteById id : " + id);
		Member member = em.find(Member.class, id);
		if(member != null) {
			// 회원 삭제
			em.remove(member);
			result = 1;
			System.out.println("JpaMemberRepository deleteById DELETE...");
		}
		else {
			System.out.println("JpaMemberRepository deleteById NO EXIST...");
		}
		
		return result;
	}

}

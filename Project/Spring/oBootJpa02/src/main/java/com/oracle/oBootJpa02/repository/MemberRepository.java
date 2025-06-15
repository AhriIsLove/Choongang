package com.oracle.oBootJpa02.repository;

import java.math.BigDecimal;
import java.util.List;

import com.oracle.oBootJpa02.domain.Member;

public interface MemberRepository {
	Member memberSave(Member member);
	List<Member> findAll();
	List<Member> findByNames(String searchName);
	Member findByMember(BigDecimal memberId);
	int updateByMember(Member member);
	List<Member> findByMembers(BigDecimal id, Long sal);
}

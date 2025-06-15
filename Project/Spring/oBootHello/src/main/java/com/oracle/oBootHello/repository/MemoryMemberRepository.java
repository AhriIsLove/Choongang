package com.oracle.oBootHello.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.oracle.oBootHello.dto.Member1;

@Repository
public class MemoryMemberRepository implements MemberRepository {
	private static Map<Long, Member1> store = new HashMap<>();
	private static Long sequence = 0L;
	
	@Override
	public Member1 save(Member1 member1) {
		// TODO Auto-generated method stub
		member1.setId(++sequence);
		store.put(member1.getId(), member1);
		
		System.out.println("MemoryMemberRepository sequence : " + sequence);
		System.out.println("MemoryMemberRepository member1 : " + member1);
		
		return member1;
	}

	@Override
	public List<Member1> findAll() {
		// TODO Auto-generated method stub
		System.out.println("MemoryMemberRepository findAll start...");
		//store의 value(Member1객체)만 가져와서 리스트로 저장
		List<Member1> listMember = new ArrayList<>(store.values());
		
		return listMember;
	}

}

package com.oracle.oBootDbConnect.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.oracle.oBootDbConnect.domain.Member7;

//@Repository : 얘 대신 JdbcMemberRepository사용
public class MemoryMemberRepository implements MemberRepository {
	private static Map<Long, Member7> store = new HashMap<>();
	private static Long sequence = 0L;

	@Override
	public Member7 save(Member7 member7) {
		// TODO Auto-generated method stub
		member7.setId(++sequence);
		store.put(member7.getId(), member7);
		
		System.out.println("MemoryMemberRepository sequence : " + sequence);
		System.out.println("MemoryMemberRepository member7 : " + member7);
		
		return member7;
	}

	@Override
	public List<Member7> findAll() {
		// TODO Auto-generated method stub
		System.out.println("MemoryMemberRepository findAll start...");
		//store의 value(Member1객체)만 가져와서 리스트로 저장
		List<Member7> listMember = new ArrayList<>(store.values());
		
		return listMember;
	}

}

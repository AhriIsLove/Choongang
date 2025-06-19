package com.oracle.oBootMybatis01.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.oracle.oBootMybatis01.dto.Member1;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class Member1DaoImpl implements Member1Dao {
	// Mybatis DB 연동
	private final SqlSession session;
	
	@Override
	public int memCount(String id) {
		//Mapper : Member1.xml
		//result = session.selectOne("memCount", id);
		int result = 0;
		
		try {
			result = session.selectOne("memCount", id);
			System.out.println("memCount = " + result);
		} catch (Exception e) {
			System.out.println("memCount ERROR : " + e.getMessage());
		}
		
		return result;
	}

	@Override
	public List<Member1> listMem(Member1 member1) {
		List<Member1> listMem = null;
		
		try{
			listMem = session.selectList("listMember1", member1);
			System.out.println("listMem o((>ω< ))o 성공");
		} catch (Exception e) {
			System.out.println("listMem o((>ω< ))o 실패");
		}
		
		return listMem;
	}

}

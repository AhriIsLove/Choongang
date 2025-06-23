package com.oracle.oBootMybatis01.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.oracle.oBootMybatis01.dto.Member1;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class Member1DaoImpl implements Member1Dao {
	// Mybatis DB 연동
	private final SqlSession session;
	private final PlatformTransactionManager transactionManager;
	
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

	@Override
	public int transactionInsertUpdate() {
		int result = 0;
		System.out.println("transactionInsertUpdate (DAO) S");
		
		Member1 member1 = new Member1();
		Member1 member2 = new Member1();
		
		try {
			//Insert 1005 : 성공예정
			member1.setId("1005");
			member1.setPassword("2345");
			member1.setName("강유6");
			result = session.insert("insertMember1", member1);
			System.out.println("transactionInsertUpdate (DAO) 1");

			//Insert 1005 : 실패예정(pk중복)
			member2.setId("1005");
			member2.setPassword("3457");
			member2.setName("이순신7");
			result = session.insert("insertMember1", member2);
			System.out.println("transactionInsertUpdate (DAO) 2");
		} catch (Exception e) {
			System.out.println("transactionInsertUpdate (DAO) 1~2 Exception" + e.getMessage());
			result = -1;
		}
		
		//결과
		// - 첫번째 member는 삽입 성공
		// - 두번쨰 member는 삽입 실패
		// -- SqlSession : 한번 실행할때마다 자동 commit
		System.out.println("transactionInsertUpdate (DAO) E");
		return result;
	}

	@Override
	public int transactionInsertUpdate3() {
		int result = 0;
		System.out.println("transactionInsertUpdate (DAO) S");
		
		Member1 member1 = new Member1();
		Member1 member2 = new Member1();
		
		//트랜잭션 시작
		TransactionStatus txStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
		
		try {
			//Insert 1005 : 성공예정
			member1.setId("1007");
			member1.setPassword("2345");
			member1.setName("이순신");
			result = session.insert("insertMember1", member1);
			System.out.println("transactionInsertUpdate (DAO) 1");

			//Insert 1005 : 실패예정(pk중복)
//			member2.setId("1007");
			member2.setId("1008");
			member2.setPassword("3457");
			member2.setName("위연");
			result = session.insert("insertMember1", member2);
			System.out.println("transactionInsertUpdate (DAO) 2");
			
			//commit
			transactionManager.commit(txStatus);
			System.out.println("transactionInsertUpdate (DAO) 3");
		} catch (Exception e) {
			System.out.println("transactionInsertUpdate (DAO) 1~3 Exception" + e.getMessage());
			result = -1;
			
			//rollback
			transactionManager.rollback(txStatus);
		}
		
		//결과
		// - 첫번째 member는 삽입 성공
		// - 두번쨰 member는 삽입 실패
		// -- transactionManager.rollback()으로 변경 없음
		System.out.println("transactionInsertUpdate (DAO) E");
		return result;
	}

}

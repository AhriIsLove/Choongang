package com.oracle.oBootMybatis01.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class EmpDaoImpl implements EmpDao {

	private final SqlSession session;
	
	@Override
	public int totalEmp() {
		int totEmpCount = 0;
		System.out.println("EmpDaoImpl totalEmp START...");
		
		try {
			// application.yml에 작성된
			// MyBatis 환경에 따라
			// - mybatis.config-location: classpath:configuration.xml
			// 작성된 com.oracle.oBootMybatis01.dto.Emp의 Emp클래스를 
			totEmpCount = session.selectOne("com.oracle.oBootMybatis01.EmpMapper.empTotal");
			System.out.println("EmpDaoImpl totalEmp totEmpCount : " + totEmpCount);
		} catch (Exception e) {
			System.out.println("EmpDaoImpl totalEmp EXCEPTION : " + e.getMessage());			
		}
		
		return totEmpCount;
	}

}

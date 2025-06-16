package com.oracle.oBootMybatis01.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.oracle.oBootMybatis01.dto.Dept;
import com.oracle.oBootMybatis01.dto.Emp;

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

	@Override
	public List<Emp> listEmp(Emp emp) {
		List<Emp> empList = null;
		System.out.println("EmpDaoImpl listEmp START...");
		try {
			empList = session.selectList("tkEmpListAll", emp);
			System.out.println("EmpDaoImpl listEmp empList : " + empList.size());
		} catch (Exception e) {
			System.out.println("EmpDaoImpl listEmp e : " + e.getMessage());
		}
		
		return empList;
	}

	@Override
	public Emp detailEmp(int empno) {
		Emp emp = null;
		try {
			emp = session.selectOne("tkEmpSelOne", empno);
			System.out.println("EmpDaoImpl detailEmp");
		} catch (Exception e) {
			System.out.println("EmpDaoImpl detailEmp e : " + e.getMessage());
		}
		
		return emp;
	}

	@Override
	public int updateEmp(Emp pEmp) {
		int updateCount = 0;
		try {
			updateCount = session.update("tkEmpUpdate",pEmp);
			System.out.println("수정됨");
		} catch (Exception e) {
			System.out.println("수정안됨");
		}
		
		return updateCount;
	}

	@Override
	public List<Emp> listManager() {
		List<Emp> empList = session.selectList("tkSelectManager");
		
		return empList;
	}
}

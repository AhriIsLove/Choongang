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
			empList = session.selectList("tkEmpListAll3", emp);
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

	@Override
	public int insert(Emp pEmp) {
		int insertResult = 0;
		
		try {
			System.out.println(pEmp.getHiredate());
			insertResult = session.insert("insertEmp", pEmp);
			System.out.println("성공~");
		} catch (Exception e) {
			System.out.println("실패...");
		}
		
		return insertResult;
	}

	@Override
	public int deleteEmp(int empno) {
		int deleteResult = 0;
		
		try {
			deleteResult = session.delete("deleteEmp", empno);
			System.out.println("삭제 성공~");
		} catch (Exception e) {
			System.out.println("삭제 실패..." + e.getMessage());
		}
		
		return deleteResult;
	}

	@Override
	public int condTotalEmp(Emp pEmp) {
		int condEmp = 0;
		
		try {
			condEmp = session.selectOne("condEmpTotal", pEmp);
			System.out.println("조회 성공~");
		} catch (Exception e) {
			System.out.println("조회 실패...");
		}
		
		return condEmp;
	}

	@Override
	public List<Emp> listSearchEmp(Emp pEmp) {
		List<Emp> listSearchEmp = null;
		
		try {
			listSearchEmp = session.selectList("tkEmpSearchList3", pEmp);
			System.out.println("조회 성공~ listSearchEmp : " + listSearchEmp.size());
		} catch (Exception e) {
			System.out.println("조회 실패...");
		}
		
		return listSearchEmp;
	}
}

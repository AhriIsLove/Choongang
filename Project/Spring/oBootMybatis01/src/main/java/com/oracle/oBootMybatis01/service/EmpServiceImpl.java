package com.oracle.oBootMybatis01.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.oBootMybatis01.dao.DeptDao;
import com.oracle.oBootMybatis01.dao.EmpDao;
import com.oracle.oBootMybatis01.dto.Dept;
import com.oracle.oBootMybatis01.dto.Emp;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmpServiceImpl implements EmpService {

	private final EmpDao ed;
	private final DeptDao dd;
	
	@Override
	public int totalEmp() {
		System.out.println("EmpServiceImpl totalEmp START...");
		int totEmpCnt = ed.totalEmp();
		System.out.println("EmpServiceImpl totalEmp totEmpCnt : " + totEmpCnt);
		
		return totEmpCnt;
	}

	@Override
	public List<Emp> listEmp(Emp emp) {
		List<Emp> empList = null;
		System.out.println("EmpServiceImpl listEmp Start...");
		empList = ed.listEmp(emp);
		System.out.println("EmpServiceImpl listEmp empList : " + empList.size());
		
		return empList;
	}

	@Override
	public Emp detailEmp(int empno) {
		Emp emp = ed.detailEmp(empno);
		
		return emp;
	}

	@Override
	public int updateEmp(Emp pEmp) {
		int updateCount = ed.updateEmp(pEmp);
		
		return updateCount;
	}

	@Override
	public List<Emp> listManager() {
		List<Emp> empList = ed.listManager();
		
		return empList;
	}

	@Override
	public List<Dept> deptSelect() {
		List<Dept> deptList = dd.deptSelect();
		
		return deptList;
	}

	@Override
	public int insert(Emp pEmp) {
		int insertResult = ed.insert(pEmp);
		
		return insertResult;
	}

	@Override
	public int deleteEmp(int empno) {
		int deleteResult = ed.deleteEmp(empno);
		
		return deleteResult;
	}

	@Override
	public int condTotalEmp(Emp pEmp) {
		int condEmp = ed.condTotalEmp(pEmp);
		
		return condEmp;
	}

	@Override
	public List<Emp> listSearchEmp(Emp pEmp) {
		List<Emp> listSearchEmp = ed.listSearchEmp(pEmp);
		
		return listSearchEmp;
	}
}

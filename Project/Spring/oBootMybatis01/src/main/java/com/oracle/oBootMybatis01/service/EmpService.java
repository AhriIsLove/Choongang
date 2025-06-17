package com.oracle.oBootMybatis01.service;

import java.util.List;

import com.oracle.oBootMybatis01.dto.Dept;
import com.oracle.oBootMybatis01.dto.Emp;

public interface EmpService {
	int totalEmp();

	List<Emp> listEmp(Emp emp);

	Emp detailEmp(int empno);

	int updateEmp(Emp pEmp);

	List<Emp> listManager();

	List<Dept> deptSelect();

	int insert(Emp pEmp);

	int deleteEmp(int empno);

	int condTotalEmp(Emp pEmp);

	List<Emp> listSearchEmp(Emp pEmp);
}

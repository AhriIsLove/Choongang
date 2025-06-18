package com.oracle.oBootMybatis01.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

//Join 목적
@Data
public class EmpDept {
	// Emp
	private int empno;
	private String ename;
	private String job;
	private int mgr;
	private String hiredate;
	private int sal;
	private int comm;
	
	// Join
	private int deptno;

	// Dept
	private String dname;
	private String loc;
}

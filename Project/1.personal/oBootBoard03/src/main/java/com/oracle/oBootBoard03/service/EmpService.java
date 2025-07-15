package com.oracle.oBootBoard03.service;

import java.util.List;

import com.oracle.oBootBoard03.dto.EmpDto;

public interface EmpService {
	Long 			totalEmp();
	List<EmpDto>	empList(EmpDto empDto);
	int             register(EmpDto empDto);
	EmpDto          getSingleEmp(int emp_no);
	
	
}

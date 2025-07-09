package com.oracle.oBootBoard03.service;

import java.util.List;

import com.oracle.oBootBoard03.dto.EmpDto;

public interface EmpService {
	int 			totalEmp();
	List<EmpDto>	empList(EmpDto empDto);
	
}

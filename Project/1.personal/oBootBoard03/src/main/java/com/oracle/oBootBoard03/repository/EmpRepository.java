package com.oracle.oBootBoard03.repository;

import java.util.List;

import com.oracle.oBootBoard03.domain.Emp;
import com.oracle.oBootBoard03.dto.DeptDto;
import com.oracle.oBootBoard03.dto.EmpDto;

public interface EmpRepository {
	List<Emp> 		findAllEmp();
	Emp         	empSave(Emp emp);
	Long        	empTotalcount();
	List<EmpDto> 	findPageEmp(EmpDto empDto);
	EmpDto          findById(int emp_no); 
}

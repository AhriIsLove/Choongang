package com.oracle.oBootMybatis01.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.oBootMybatis01.dao.EmpDao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmpServiceImpl implements EmpService {

	private final EmpDao ed;
	
	@Override
	public int totalEmp() {
		System.out.println("EmpServiceImpl totalEmp START...");
		int totEmpCnt = ed.totalEmp();
		System.out.println("EmpServiceImpl totalEmp totEmpCnt : " + totEmpCnt);
		
		return totEmpCnt;
	}

}

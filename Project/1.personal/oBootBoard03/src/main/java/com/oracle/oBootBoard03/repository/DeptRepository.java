package com.oracle.oBootBoard03.repository;

import java.util.List;

import com.oracle.oBootBoard03.domain.Dept;

public interface DeptRepository {
	List<Dept> findAllDept();
	Dept deptSave(Dept dept);
}

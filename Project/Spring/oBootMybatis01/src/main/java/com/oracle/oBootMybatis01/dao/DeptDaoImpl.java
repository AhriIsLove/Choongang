package com.oracle.oBootMybatis01.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.oracle.oBootMybatis01.dto.Dept;
import com.oracle.oBootMybatis01.dto.DeptVO;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class DeptDaoImpl implements DeptDao {
	private final SqlSession session;

	@Override
	public List<Dept> deptSelect() {
		List<Dept> deptList = null;
		
		try {
			deptList = session.selectList("tkSelectDept");
		} catch (Exception e) {
			System.out.println("tkSelectDept 오류 : " + e.getMessage());
		}
		
		return deptList;
	}

	@Override
	public void insertDept(DeptVO deptVO) {
		session.selectOne("procDeptInsert", deptVO);
		
	}

	@Override
	public void selListDept(HashMap<String, Object> map) {
		session.selectOne("procDeptList", map);
	}

}

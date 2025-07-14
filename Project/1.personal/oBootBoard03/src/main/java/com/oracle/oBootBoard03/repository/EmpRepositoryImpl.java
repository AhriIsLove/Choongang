package com.oracle.oBootBoard03.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.oracle.oBootBoard03.domain.Emp;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class EmpRepositoryImpl implements EmpRepository {

	private final EntityManager em;
	
	@Override
	public List<Emp> findAllEmp() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Emp empSave(Emp emp) {
		System.out.println("EmpRepositoryImpl empSave emp->"+emp);
		em.persist(emp);
		return emp;
	}

}

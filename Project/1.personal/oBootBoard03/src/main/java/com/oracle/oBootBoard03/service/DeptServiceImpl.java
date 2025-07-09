package com.oracle.oBootBoard03.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.oBootBoard03.domain.Dept;
import com.oracle.oBootBoard03.dto.DeptDto;
import com.oracle.oBootBoard03.repository.DeptRepository;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Transactional
@Log4j2
@RequiredArgsConstructor
public class DeptServiceImpl implements DeptService {

	//자동주입 대상은 final로 
    private final ModelMapper modelMapper;
    private final DeptRepository deptRepository;

	@Override
	public Long totalDept() {
		System.out.println("DeptServiceImpl dept/list Strart...");
		Long totalCount =  deptRepository.deptTotalcount();
		return totalCount;
	}

	@Override
	public List<DeptDto> deptList(DeptDto deptDto) {
	    List<DeptDto> deptRtnList = deptRepository.findPageDept(deptDto);
	    System.out.println("DeptServiceImpl deptList deptRtnList->"+deptRtnList);	
	    return deptRtnList;
	 }

	@Override
	public int deptSave(DeptDto deptDto) {
		log.info("DeptServiceImpl deptSave start deptDto->"+deptDto);
		Dept dept = modelMapper.map(deptDto, Dept.class);
		if(dept.getDept_gubun()==null) dept.changeDept_gubun(false);
		Dept saveDept = deptRepository.deptSave(dept);
		return saveDept.getDept_code();
	}

}

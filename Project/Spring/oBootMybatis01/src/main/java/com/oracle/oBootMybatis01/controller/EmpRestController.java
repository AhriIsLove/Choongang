package com.oracle.oBootMybatis01.controller;

import java.util.List;

//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.oracle.oBootMybatis01.dto.Dept;
import com.oracle.oBootMybatis01.dto.SampleVO;
import com.oracle.oBootMybatis01.service.EmpService;

import lombok.RequiredArgsConstructor;

@RestController
//@RestController
// - @Controller
// - @ResponseBody
// -- return이 ViewResolver로 가지 않는다
// -- StringConverter/JsonCOnverter로 간다.
// --- Body로 들어간다
@RequiredArgsConstructor
public class EmpRestController {
	private final EmpService es;
	
	@RequestMapping("/helloText")
	public String helloText() {
		String hello = "안녕";
		
		// StringConverter
		return hello;
	}
	
	@RequestMapping("/sample/sendVO2")
	public SampleVO sendVO2(Dept dept) {
		SampleVO vo = new SampleVO();
		vo.setFirstName("길동");
		vo.setLastName("홍");
		vo.setMno(dept.getDeptno());
		
		// JsonConverter
		// jsonviewer.stack.hu 에서 보기쉽게 변환 가능
		return vo;
	}
	
	@RequestMapping("/sendVO3")
	public List<Dept> sendVO3() {
		List<Dept> deptList = es.deptSelect();

		return deptList;
	}
}

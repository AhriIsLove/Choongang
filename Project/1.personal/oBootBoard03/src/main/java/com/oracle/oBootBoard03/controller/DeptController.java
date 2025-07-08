package com.oracle.oBootBoard03.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oracle.oBootBoard03.dto.DeptDto;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/dept")
public class DeptController {
	@GetMapping("/list")
	public String mainPage() {
//		System.out.println("dept/list Page");

		return "dept/list";
	}
	
	@GetMapping("deptInForm")
	public String deptInForm() {
//		System.out.println("deptInForm Page");
		
		return "dept/deptInForm";
	}
	
	@PostMapping("saveDept")
	public String saveDept(DeptDto deptDto) {
		System.out.println("saveDept deptDto : " + deptDto);
		
		return "dept/list";
	}
}

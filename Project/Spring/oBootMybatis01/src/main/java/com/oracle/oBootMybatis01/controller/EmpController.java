package com.oracle.oBootMybatis01.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oracle.oBootMybatis01.dto.Dept;
import com.oracle.oBootMybatis01.dto.Emp;
import com.oracle.oBootMybatis01.service.EmpService;
import com.oracle.oBootMybatis01.service.Paging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
@Slf4j
public class EmpController {
	private final EmpService es;
	
	@RequestMapping("/listEmpStart")
	public String listEmpStart(Emp emp, Model model) {
		System.out.println("EmpController listEmpStart START...");
		
		//모든 사원 수
		int totalEmp = es.totalEmp();
		model.addAttribute("totalEmp", totalEmp);
		
		String currentPage = "1";
		Paging paging = new Paging(totalEmp, currentPage);
		emp.setStart(paging.getStart());
		emp.setEnd(paging.getEnd());
		
		List<Emp> listEmp = es.listEmp(emp);
		System.out.println("EmpController listEmpStart listEmp : " + listEmp.size());
		
//		model.addAttribute("totalEmp", totalEmp);
		model.addAttribute("listEmp", listEmp);
		model.addAttribute("page", paging);
		
		return "list";
	}
	
	@RequestMapping("/listEmp")
	public String listEmp(Emp emp, Model model) {
		System.out.println("EmpController listEmpStart START...");
		
		//모든 사원 수
		int totalEmp = es.totalEmp();
		model.addAttribute("totalEmp", totalEmp);
		
		Paging paging = new Paging(totalEmp, emp.getCurrentPage());
		emp.setStart(paging.getStart());
		emp.setEnd(paging.getEnd());
		
		List<Emp> listEmp = es.listEmp(emp);
		System.out.println("EmpController listEmpStart listEmp : " + listEmp.size());
		
//		model.addAttribute("totalEmp", totalEmp);
		model.addAttribute("listEmp", listEmp);
		model.addAttribute("page", paging);
		
		return "list";
	}

//	1. EmpService안에 detailEmp method 선언
//	   1) parameter : empno
//	   2) Return    : Emp
//	2. EmpDao   detailEmp method 선언 
////						mapper ID   ,    Parameter
//	emp = session.selectOne("tkEmpSelOne",    empno);
	@GetMapping("/detailEmp")
	public String detailEmp(Emp pEmp, Model model) {

		Emp emp = es.detailEmp(pEmp.getEmpno());
		model.addAttribute("emp", emp);
		
		return "detailEmp";
	}
	
	@GetMapping("/updateFormEmp")
	public String updateFormEmp(Emp pEmp, Model model) {
		Emp emp = es.detailEmp(pEmp.getEmpno());
		
		// 문제 
		// 1. DTO  String hiredate
		// 2.View : 단순조회 OK ,JSP에서 input type="date" 문제 발생
		// 3.해결책  : 년월일만 짤라 넣어 주어야 함
		String hiredate = "";
		if(emp.getHiredate() != null) {
			hiredate = emp.getHiredate().substring(0, 10);
			emp.setHiredate(hiredate);
		}
		
		model.addAttribute("emp", emp);
		
		return "updateFormEmp";
	}
	
	@PostMapping("/updateEmp")
	public String updateEmp(Emp pEmp, Model model) {
		log.info("updateEmp START...");
		
//		1. EmpService안에 updateEmp method 선언
//			1) parameter : Emp
//			2) Return      updateCount (int)
//		
//		2. EmpDao updateEmp method 선언
////		                           mapper ID   ,Parameter
//		updateCount = session.update("tkEmpUpdate",emp);
		
		int updateCount = es.updateEmp(pEmp);
		
		model.addAttribute("uptCnt", updateCount);
		model.addAttribute("kk3", "Message Test");
		
		//model의 addAttribute를 가져가지 않음 : 새롭게 listEmp를 로드하기 때문에 가져가도 없어짐
//		return "redirect:listEmp";
		//파라미터를 가지고 간다.
		return "forward:listEmp";
	}
	
	@RequestMapping("/writeFormEmp")
	public String writeFormEmp(Model model) {
		System.out.println("empController writeFormEmp START...");
		
		// 관리자 사번 만 Get
		// 1. service -> listManager
		List<Emp> empList = es.listManager();
		// 2. Dao     -> listManager
		// 3. mapper  -> tkSelectManager
		model.addAttribute("empMngList", empList);
		
		// 부서(코드,부서명)
		List<Dept> deptList = es.deptSelect();
		model.addAttribute("deptList", deptList);
		
		return "writeFormEmp";
	}
}

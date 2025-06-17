package com.oracle.oBootMybatis01.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oracle.oBootMybatis01.dto.Dept;
import com.oracle.oBootMybatis01.dto.Emp;
import com.oracle.oBootMybatis01.service.EmpService;
import com.oracle.oBootMybatis01.service.Paging;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
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

		// 모든 사원 수
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
	public String listEmp(Emp emp, Model model, HttpServletRequest request) {
		System.out.println("EmpController listEmp START...");

		// 모든 사원 수
		int totalEmp = es.totalEmp();
		model.addAttribute("totalEmp", totalEmp);

		System.out.println("emp.getCurrentPage() : " + emp.getCurrentPage());
		System.out.println("currentPage : " + request.getParameter("currentPage"));

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
		// 1. DTO String hiredate
		// 2.View : 단순조회 OK ,JSP에서 input type="date" 문제 발생
		// 3.해결책 : 년월일만 짤라 넣어 주어야 함
		String hiredate = "";
		if (emp.getHiredate() != null) {
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

		// model의 addAttribute를 가져가지 않음 : 새롭게 listEmp를 로드하기 때문에 가져가도 없어짐
//		return "redirect:listEmp";
		// 파라미터를 가지고 간다.
		return "forward:listEmp";
	}

	@RequestMapping("/writeFormEmp")
	public String writeFormEmp(Model model) {
		System.out.println("empController writeFormEmp START...");

		// 관리자 사번 만 Get
		// 1. service -> listManager
		List<Emp> empList = es.listManager();
		// 2. Dao -> listManager
		// 3. mapper -> tkSelectManager
		model.addAttribute("empMngList", empList);

		// 부서(코드,부서명)
		List<Dept> deptList = es.deptSelect();
		model.addAttribute("deptList", deptList);

		return "writeFormEmp";
	}

	@PostMapping("/writeEmp")
	public String writeEmp(Emp pEmp, Model model) {
//		Service, Dao , Mapper명[insertEmp] 까지 -> insert
//		int insertResult 결과받기

		int insertResult = es.insert(pEmp);

		if (insertResult > 0)
			return "redirect:/listEmp";
		else {
			model.addAttribute("msg", "입력 실패!");
			return "forward:/writeFormEmp";
		}
	}

//	Validation CHECK
	@RequestMapping("/writeFormEmp3")
	public String writeFormEmp3(Model model) {
		System.out.println("empController writeFormEmp3 START...");

		// 관리자 사번 만 Get
		// 1. service -> listManager
		List<Emp> empList = es.listManager();
		// 2. Dao -> listManager
		// 3. mapper -> tkSelectManager
		model.addAttribute("empMngList", empList);

		// 부서(코드,부서명)
		List<Dept> deptList = es.deptSelect();
		model.addAttribute("deptList", deptList);

		return "writeFormEmp3";
	}

//	Validation CHECK
	@PostMapping("/writeEmp3")
	public String writeEmp3(@ModelAttribute("emp") @Valid Emp pEmp, BindingResult result, Model model) {
		// Validation CHECK 오류시
		if (result.hasErrors()) {
			System.out.println("alidation CHECK 오류");
			model.addAttribute("msg", "BindingResult 입력 실패 확인하세요");
			return "forward:/writeFormEmp3";
		}

		int insertResult = es.insert(pEmp);

		if (insertResult > 0)
			return "redirect:/listEmp";
		else {
			model.addAttribute("msg", "입력 실패!");
			return "forward:/writeFormEmp3";
		}
	}

	@GetMapping("/confirm")
	public String confirm(Emp pEmp, Model model) {
		Emp emp = es.detailEmp(pEmp.getEmpno());
		model.addAttribute("empno", pEmp.getEmpno());
		if (emp != null) {
			model.addAttribute("msg", "중복된 사번입니다.");
		} else {
			model.addAttribute("msg", "사용 가능한 사번입니다.");
		}

		return "forward:/writeFormEmp";
	}

	@GetMapping("/deleteEmp")
	public String deleteEmp(Emp pEmp, Model model) {
		// Controller --> deleteEmp 1.parameter : empno
		// name -> Service, dao , mapper
		int deleteResult = es.deleteEmp(pEmp.getEmpno());

//		model.addAttribute("currentPage", "2");

		return "forward:/listEmp";
	}

	@RequestMapping("/listSearch3")
	public String listSearch3(Emp pEmp, Model model) {
		System.out.println("listSearch3.pEmp : " + pEmp);

		// 1. Emp Search Count
		int condEmp = es.condTotalEmp(pEmp);
		System.out.println("listSearch3.condEmp : " + condEmp);
		model.addAttribute("totalEmp", condEmp);
		
		// 2. Paging 작업
		Paging page = new Paging(condEmp, pEmp.getCurrentPage());
		pEmp.setStart(page.getStart());
		pEmp.setEnd(page.getEnd());
		model.addAttribute("page", page);
		
		// 3. listSearchEmp
		List<Emp> listSearchEmp = es.listSearchEmp(pEmp);
		model.addAttribute("listEmp", listSearchEmp);

		return "list";
	}
}

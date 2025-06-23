package com.oracle.oBootMybatis01.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oracle.oBootMybatis01.dto.Dept;
import com.oracle.oBootMybatis01.dto.Emp;
import com.oracle.oBootMybatis01.service.EmpService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class AjaxController {
	private final EmpService es;

	@RequestMapping("/ajaxForm")
	public String ajaxForm() {
		return "ajaxForm";
	}

	@RequestMapping("/getDeptName")
	@ResponseBody
	public String getDeptName(Dept dept, Model model) {
		String deptName = es.deptName(dept.getDeptno());

		return deptName;
	}

	// Ajax List Test
	@RequestMapping("/listEmpAjaxForm")
	public String listEmpAjaxForm(Model model) {
		Emp emp = new Emp();
		// 10명만 데려온다.
		emp.setStart(1);
		emp.setEnd(10);

		List<Emp> listEmp = es.listEmp(emp);
		model.addAttribute("result", "kkk");
		model.addAttribute("listEmp", listEmp);

		return "listEmpAjaxForm";
	}

	@RequestMapping("/empSerializeWrite")
	@ResponseBody
	public Map<String, Object> empSerializeWrite(@RequestBody /* Json으로 받는 데이터 */ @Valid Emp emp) {
		int writeResult = 1;

		// Emp에 추가
		// int writeResult = es.writeEmp(emp);

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("writeResult", writeResult);
		resultMap.put("anyResult", "anyR");

		return resultMap;
	}

	@RequestMapping("/listEmpAjaxForm2")
	public String listEmpAjaxForm2(Model model) {
		Emp emp = new Emp();
		// 15명만 데려온다.
		emp.setStart(1);
		emp.setEnd(15);

		List<Emp> listEmp = es.listEmp(emp);
		model.addAttribute("listEmp", listEmp);

		return "listEmpAjaxForm2";
	}

	@RequestMapping("/empnoDelete")
	@ResponseBody
	public String empnoDelete(Emp emp) {
		int delStatus = es.deleteEmp(emp.getEmpno());
		String delStatusStr = Integer.toString(delStatus);
		return delStatusStr;
	}

	@RequestMapping("/empnoDelete03")
	@ResponseBody
	public Map<String, Object> empnoDelete03(Emp emp) {
		int delStatus = es.deleteEmp(emp.getEmpno());
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("delStatus", delStatus);
		return resultMap;
	}

	@RequestMapping("/listEmpAjaxForm3")
	public String listEmpAjaxForm3(Model model) {
		Emp emp = new Emp();
		// 10명만 데려온다.
		emp.setStart(1);
		emp.setEnd(15);

		List<Emp> listEmp = es.listEmp(emp);
		model.addAttribute("result", "kkk");
		model.addAttribute("listEmp", listEmp);

		return "listEmpAjaxForm3";
	}
	
	@RequestMapping("/empListUpdate")
	@ResponseBody
	public Map<String, Object> empListUpdate(@RequestBody @Valid List<Emp> listEmp) {
		int updateResult = 0;
		
		for(Emp emp : listEmp) {
			System.out.println("emp : " + emp);
			
//			updateResult = es.listUpdateEmp(emp);
			updateResult = 1;
		}
		
		Map<String, Object> response = new HashMap<>();
		response.put("updateResult", updateResult);
		return response;
	}
	
	@RequestMapping("/transactionInsertUpdate")
	@ResponseBody
	public String transactionInsertUpdate(Emp emp, Model model) {
		System.out.println("transactionInsertUpdate S");
		int returnMember = 0;
		
		returnMember = es.transactionInsertUpdate();
		
		System.out.println("transactionInsertUpdate E : " + returnMember);
		String returnMemberString = String.valueOf(returnMember);
		return returnMemberString;
	}
}

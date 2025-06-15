package com.oracle.oBootHello.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oracle.oBootHello.dto.Emp;

// DispatcherServlet에게 알려주는 기호 @
// MVC중 Controller 선언
@Controller
public class HelloController {

	private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

	// HandlerMapping을 위한 이름 선언
	@RequestMapping("hello")
	public String hello(Model model) {
		System.out.println("HelloController hello start...");

		LOGGER.info("start...");

		// 파라미터 전달
		model.addAttribute("parameter", "Hello Start");

		// ViewResolver로 요청 : templates/hello.html
		return "hello";
	}

	// ResponseBody : return시 HttpMessageConverter로 요청
	@ResponseBody
	// HandlerMapping(Get)을 위한 이름 선언
	@GetMapping("ajaxString")
	public String ajaxString(@RequestParam("ajaxName") String aName) {
		System.out.println("HelloController ajaxString aName : " + aName);

		// @ResponseBody이기 때문에
		// HttpMessageConverter로 요청
		/// dataType = String : StringConverter
		/// dataType = Object : JsonConverter
		return aName;
	}

	@ResponseBody
	@GetMapping("ajaxEmp")
	public Emp ajaxEmp(@RequestParam("empno") String empno, @RequestParam("ename") String ename) {
		LOGGER.info("HelloController ajaxEmp empno : " + empno);
		LOGGER.info("ename : {}", ename);
		
		Emp emp = new Emp();
		emp.setEmpno(empno);
		emp.setEname(ename);
		
		System.out.println("emp : " + emp);
		LOGGER.info("emp : {}", emp);

		// HttpMessageConverter로 요청
		// Object리턴 : JsonConverter
		return emp;
	}
}

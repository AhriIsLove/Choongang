package com.oracle.oBootBoard03.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.oracle.oBootBoard03.dto.DeptDto;
import com.oracle.oBootBoard03.service.DeptService;
import com.oracle.oBootBoard03.service.Paging;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/dept")
public class DeptController {

	private final DeptService deptService;
	
	@GetMapping("/list")
	public String listPage(DeptDto deptDto , Model model) {
		System.out.println("dept/list Strart...");
		Long totalCountLong =  deptService.totalDept();	
		int totalCountInt = totalCountLong.intValue(); 
		System.out.println("1.DeptController list totalCount->"+totalCountInt);
		Paging page = new Paging(totalCountInt, deptDto.getCurrentPage());

		// Parameter deptDto --> Page만 추가 Setting
		deptDto.setStart(page.getStart());   // 시작시 1
		deptDto.setEnd(page.getEnd());       // 시작시 10 
		
		System.out.println("2. DeptController list deptDto->"+deptDto);
		List<DeptDto> deptDtoList = deptService.deptList(deptDto);
		System.out.println("3. DeptController list listDept.size()=>" + deptDtoList.size());

		model.addAttribute("totalCount", totalCountInt);
		model.addAttribute("deptDtoList" , deptDtoList);
		model.addAttribute("page"    , page);

		return "dept/list";
	}
	
	@GetMapping("/deptInForm")
	public String deptInForm() {
		System.out.println("dept/deptInForm Strart...");

		return "dept/deptInform";
	}
	
	@PostMapping("saveDept")
	public String saveDept(DeptDto deptDto) {
		System.out.println("dept/saveDept Strart...");
		System.out.println("dept/saveDept deptDto->"+deptDto);
		// 여러분의 Logic 
		int dept_code = deptService.deptSave(deptDto);
		log.info("Save dept_code-->", dept_code);
		return "dept/list";
		
	}
}

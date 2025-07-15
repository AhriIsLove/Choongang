package com.oracle.oBootBoard03.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.oracle.oBootBoard03.domain.Emp;
import com.oracle.oBootBoard03.dto.DeptDto;
import com.oracle.oBootBoard03.dto.EmpDto;
import com.oracle.oBootBoard03.service.DeptService;
import com.oracle.oBootBoard03.service.EmpService;
import com.oracle.oBootBoard03.service.Paging;
import com.oracle.oBootBoard03.util.CustomFileUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/emp")
public class EmpController {
	
	private final EmpService empService;
	private final DeptService deptService;
	private final CustomFileUtil fileUtil;
	
//	@GetMapping("/list")
//	public String mainPage() {
//		System.out.println("emp/list Strart...");
//		return "emp/list";
//	}
	
	@GetMapping("/empInForm")
	public String deptInForm() {
		System.out.println("emp/empInForm Strart...");
		return "emp/empInForm";
	}

	@RequestMapping("/saveEmp")
	public String saveEmp( 
			        EmpDto empDto ,
			        //@RequestParam("files") List<MultipartFile> files,
			        // @RequestParam(value = "files", required = false) MultipartFile[] files,  // List 대신 배열로 받기
			        Model model) 
		     {
	    System.out.println("saveEmp file start...");

	    // List<MultipartFile> filesList = Arrays.asList(files);
	    List<MultipartFile> files = empDto.getFiles();

	    log.info("1. rgister empDto:" + empDto);
	    // File Upload
	    List<String> uploadFileNames = fileUtil.saveFiles(files);
	    empDto.setUploadFileNames(uploadFileNames);
	    log.info(uploadFileNames);
	    log.info("2. rgister empDto:" + empDto);
		
//	    // File Upload + Emp 내용 --> Emp Insert
	    int emp_no = empService.register(empDto);
		System.out.println("emp/empInForm saveEmp emp_no->"+emp_no);
	    return  "redirect:list";
	    
	}

	@GetMapping("/list")
	public String listPage(EmpDto empDto , Model model) {
		System.out.println("emp/list Strart...");
		Long totalCountLong =  empService.totalEmp();	
		int totalCountInt = totalCountLong.intValue(); 
		System.out.println("1.EmpController list totalCount->"+totalCountInt);
		Paging page = new Paging(totalCountInt, empDto.getCurrentPage());

		// Parameter deptDto --> Page만 추가 Setting
		empDto.setStart(page.getStart());   // 시작시 1
		empDto.setEnd(page.getEnd());       // 시작시 10 
		
		System.out.println("2. EmpController list empDto->"+empDto);
		List<EmpDto> empDtoList = empService.empList(empDto);
  
		System.out.println("3. EmpController list empDtoList.size()=>" + empDtoList.size());
		System.out.println("5. EmpController list empDtoList=>" + empDtoList);

		model.addAttribute("totalCount", totalCountInt);
		model.addAttribute("empDtoList" , empDtoList);
		model.addAttribute("page"    , page);

		return "emp/list";
	}
	
	
	@GetMapping("/empDetail")
	public String deptDetail(EmpDto empDto  , Model model) {
		System.out.println("EmpController emp/empDetail Start...");
		System.out.println("1.EmpController emp/empDetail empDto->"+empDto);
		EmpDto empRtnDto = empService.getSingleEmp(empDto.getEmp_no());
		System.out.println("2.EmpController emp/empDetail empRtnDto->"+empRtnDto);
		model.addAttribute("empDto", empRtnDto);

		return "emp/empDetail";
	}


	@GetMapping("/modifyForm")
	public String modifyForm(EmpDto empDto  , Model model) {
		System.out.println("EmpController dept/modifyForm Start...");
		System.out.println("EmpController dept/modifyForm empDto->"+empDto);
		EmpDto empRtnDto  = empService.getSingleEmp(empDto.getEmp_no());
		List<DeptDto> deptDtoList = deptService.deptAllList();
		System.out.println("3. DeptController list listDept.size()=>" + deptDtoList.size());

		model.addAttribute("empDto",      empRtnDto);
		model.addAttribute("deptDtoList", deptDtoList);

		return "emp/empModifyForm";
	}	
	
	
}

package com.oracle.oBootBoard03.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.oracle.oBootBoard03.dto.EmpDto;
import com.oracle.oBootBoard03.service.EmpService;
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
	private final CustomFileUtil fileUtil;
	
	@GetMapping("/list")
	public String mainPage() {
		System.out.println("emp/list Strart...");
		return "emp/list";
	}
	
	@GetMapping("/empInForm")
	public String deptInForm() {
		System.out.println("emp/empInForm Strart...");
		return "emp/empInForm";
	}

	@RequestMapping("/saveEmp")
	public String saveEmp( 
			        EmpDto empDto ,
			        // @RequestParam("files") List<MultipartFile> files,
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

}

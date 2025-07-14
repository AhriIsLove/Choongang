package com.oracle.oBootBoard03.dto;

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmpDto {
	private int 		emp_no;
	private String      emp_id;
	private String  	emp_password;
	private String  	emp_name;
	private String  	email;
	private String  	emp_tel;
	private Long    	sal;
	private Boolean  	del_status;
	private int         dept_code;
	private int         emp_level;
	private LocalDate   in_date;
	
	// 조회용
	private String simage;
	private String pageNum;  
	private int start; 		 	   
	private int end;
	// Page 정보
	private String currentPage;

	@Builder.Default
	private List<MultipartFile> files = new ArrayList<>();
	@Builder.Default
	private List<String> uploadFileNames = new ArrayList<>();

}

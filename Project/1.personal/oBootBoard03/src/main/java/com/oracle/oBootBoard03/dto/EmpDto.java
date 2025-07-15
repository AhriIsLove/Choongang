package com.oracle.oBootBoard03.dto;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

import com.oracle.oBootBoard03.domain.Dept;
import com.oracle.oBootBoard03.domain.Emp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmpDto {
	private int 			emp_no;
	private String      	emp_id;
	private String  		emp_password;
	private String  		emp_name;
	private String  		email;
	private String  		emp_tel;
	private Long    		sal;
	private Boolean  		del_status;
	private int         	dept_code;
	private int         	emp_level;
	private LocalDateTime   in_date;
	
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
	// List 조회시 부서명
	private String dept_name;
	
    // 2. 엔티티 객체를 받아서 DTO로 변환하는 생성자 (더 편리!)
    public EmpDto(Emp emp) {
        this.emp_no 	= emp.getEmp_no();
        this.dept_code 	= emp.getDept_code();
        this.dept_name 	= emp.getDept_name();
        this.email   	= emp.getEmail();
        this.emp_id 	= emp.getEmp_id();
        this.emp_level 	= emp.getEmp_level();
        this.emp_name   = emp.getEmp_name();
        this.emp_tel    = emp.getEmp_tel();
    }
}

package com.oracle.oBootBoard03.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class DeptDto {
	private int dept_code;
	private String dept_name;
	private int dept_cpatain;
	private String dept_tel;
	private String dept_loc;
	private LocalDate in_date;
	private boolean dept_gubun;
}

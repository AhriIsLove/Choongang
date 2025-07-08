package com.oracle.oBootBoard03.domain;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(
		name = "deptcode_seq",
		sequenceName = "deptcode_seq_gen",
		allocationSize = 1,
		initialValue = 1000)
public class Dept {
	@Id
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "deptcode_seq")
	private int dept_code;
	private String dept_name;
	private int dept_cpatain;
	private String dept_tel;
	private String dept_loc;
	private LocalDate in_date;
	private boolean dept_gubun;
	
	public void changeDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	public void changeDept_cpatain(int dept_cpatain) {
		this.dept_cpatain = dept_cpatain;
	}

	public void changeDept_tel(String dept_tel) {
		this.dept_tel = dept_tel;
	}

	public void changeDept_loc(String dept_loc) {
		this.dept_loc = dept_loc;
	}

	public void changeDept_gubun(boolean dept_gubun) {
		this.dept_gubun = dept_gubun;
	}

}

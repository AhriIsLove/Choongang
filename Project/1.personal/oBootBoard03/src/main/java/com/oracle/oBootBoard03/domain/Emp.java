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
@SequenceGenerator(name = "emp_seq_gen", sequenceName = "emp_seq_generator", allocationSize = 1, initialValue = 1000000)
public class Emp {
	@Id
	@GeneratedValue(generator = "emp_seq_gen", strategy = GenerationType.SEQUENCE)
	private int emp_no;
	private String emp_name;
	private String email;
	private String emp_tel;
	private Long sal;
	private int dept_code;
	private LocalDate in_date;
	private boolean del_status;

	public void changeEmp_no(int emp_no) {
		this.emp_no = emp_no;
	}

	public void changeEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}

	public void changeEmail(String email) {
		this.email = email;
	}

	public void changeEmp_tel(String emp_tel) {
		this.emp_tel = emp_tel;
	}

	public void changeSal(Long sal) {
		this.sal = sal;
	}

	public void changeDept_code(int dept_code) {
		this.dept_code = dept_code;
	}

	public void changeDel_status(boolean del_status) {
		this.del_status = del_status;
	}

}

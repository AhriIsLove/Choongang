package com.oracle.oBootJpa02.domain;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;
//import lombok.EqualsAndHashCode;
//import lombok.Getter;
//import lombok.RequiredArgsConstructor;
//import lombok.Setter;
//import lombok.ToString;

@Entity
@Data
//아래 설정 전부 포함
//-@Getter
//-@Setter
//-@ToString
//-@RequiredArgsConstructor
//-@EqualsAndHashCode
@SequenceGenerator(
		name = "member_seq_gen", // 객체 seq명
		sequenceName = "member_seq_generator", // DB seq명 
		initialValue = 1, 	// 시작값
		allocationSize = 1	// 증가값
		)
@Table(name = "member2")
public class Member {
	@Id	// PK 설정
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE, // 시퀀스 설정
			generator = "member_seq_gen" // 객체 seq명
			)
	@Column(name = "member_id", precision = 5, scale = 3) // precision : 소수점 위 자리수, scale : 소수점 아래 자리수
	private BigDecimal id; // precision, scale 설정을 위해 BigDecimal 사용
	@Column(name = "userName", length = 50) // userName = USER_NAME
	private String name;
	private Long sal;
	
	// FK 설정(nullable 값으로 null 설정 가능)
	@ManyToOne
	@JoinColumn(name = "team_id") // team의 PK
	private Team team;
	
	// Entity에 반영하지 않음
	@Transient
	private String teamName;
}

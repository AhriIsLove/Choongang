package com.oracle.oBootSeqcurity01.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
//Serializable 사용이유 
//웹 애플리케이션에서 사용자 세션 정보를 안전하게 관리하고, 
//서버 재시작이나 클러스터 환경에서도 세션이 유지될 수 있도록 하기 위함
public class Account implements Serializable {
	@Id
	@GeneratedValue
	private Long id;
	@Column(unique = true)
	private String username;// Security 제공 매칭
	private String password;// Security 제공
	private String roles;// Security 제공 매칭
	private int age;
}

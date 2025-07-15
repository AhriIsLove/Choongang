package com.oracle.oBootSeqcurity01.domain;

import java.io.Serializable;

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
	private String username;
	private String password;
	private String roles;
	private int age;
}

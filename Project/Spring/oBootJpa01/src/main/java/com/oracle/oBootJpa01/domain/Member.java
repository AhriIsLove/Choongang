package com.oracle.oBootJpa01.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//필수*

//테이블 지정*
@Entity
@Table(name = "member1")//테이블 명 지정
public class Member {
	//PK 지정*
	@Id
	private Long id;
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

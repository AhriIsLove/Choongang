package com.oracle.oBootDbConnect.domain;

public class Member7 {
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
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
//		return super.toString();
		return "[" + this.id + "," + this.name + "]";
	}
}

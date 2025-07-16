package com.oracle.oBootSeqcurity01.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {
	private Long id;//?
	private String username;
	private String password;
	private String roles;
	private int age;
}

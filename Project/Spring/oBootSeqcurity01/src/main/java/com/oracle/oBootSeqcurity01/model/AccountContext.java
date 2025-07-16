package com.oracle.oBootSeqcurity01.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data

public class AccountContext implements UserDetails {
	private AccountDto accountDto;
	private final List<GrantedAuthority> roles;
	
	//@AllArgsConstructor
	public AccountContext(AccountDto accountDto, List<GrantedAuthority> roles) {
		this.accountDto = accountDto;
		this.roles = roles;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return roles;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return accountDto.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return accountDto.getUsername();
	}

	//사용 가능 여부
	@Override
	public boolean isEnabled() {
//		return UserDetails.super.isEnabled();
		return true;
	}
	
	//만료 여부
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
//		return UserDetails.super.isCredentialsNonExpired();
		return true;
	}
}

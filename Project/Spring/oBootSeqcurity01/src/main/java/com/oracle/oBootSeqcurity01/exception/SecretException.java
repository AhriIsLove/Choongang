package com.oracle.oBootSeqcurity01.exception;

import org.springframework.security.core.AuthenticationException;

public class SecretException extends AuthenticationException {

	public SecretException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

}

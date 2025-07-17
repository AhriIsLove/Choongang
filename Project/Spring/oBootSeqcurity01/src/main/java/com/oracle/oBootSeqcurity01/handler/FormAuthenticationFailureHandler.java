package com.oracle.oBootSeqcurity01.handler;

import java.io.IOException;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FormAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		String errorMessage = "Invalid Username or Password";

		// BadCredentialsException : 비밀번호 다름
		if (exception instanceof BadCredentialsException) {
			errorMessage = "Invalid Password";
		}
		// UsernameNotFoundException : 사용자 찾지 못함
		else if (exception instanceof UsernameNotFoundException) {
			errorMessage = "User not exists";
		}
		// CredentialsExpiredException : 비밀번호 만료(주기적으로 변경 필요)
		else if (exception instanceof CredentialsExpiredException) {
			errorMessage = "Expired password";
		}
//		// AccountExpiredException : 계정 만료(구독 만료)
//		else if (exception instanceof AccountExpiredException) {
//			errorMessage = "Expired Acoount";
//		}
//		else if(exception instanceof ???) {
//			errorMessage = "???";			
//		}

		// 이동 페이지를 에러 페이지로 설정
		setDefaultFailureUrl("/login?error=true&exception=" + errorMessage);

		super.onAuthenticationFailure(request, response, exception);
	}
}

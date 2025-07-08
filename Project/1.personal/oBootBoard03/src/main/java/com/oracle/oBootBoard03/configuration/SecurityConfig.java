package com.oracle.oBootBoard03.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	// 해시 암호화 방식 사용
	public BCryptPasswordEncoder encodePwd() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// 보안 비활성화
		// - cors : 외부(망)와 통신이 가능하게 해준다.
		// - csrf : 외부 공격에 대한 반격기
		http.cors(cors->cors.disable()).csrf(csrf->csrf.disable());
		
		return http.build();
	}
}

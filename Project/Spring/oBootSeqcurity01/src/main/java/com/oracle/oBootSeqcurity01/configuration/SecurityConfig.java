package com.oracle.oBootSeqcurity01.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		// :: -> 메소드 참조라고 하며 람다식에서 불필요한 매개변수를 제거하는 것이 목적
		http.csrf(AbstractHttpConfigurer::disable)
				// 인가 : 인증된 사용자의 권한에 따른 접근 유무
				.authorizeHttpRequests(auth -> auth
						// permitAll() : 모든 사용자
						// 접근 가능한 파일
						.requestMatchers("/css/**", "/images/**", "/js/**", "/favicon.*", "/*/icon-*",
								"/WEB-INF/views/**")
						.permitAll()
						// 접근 가능한 경로
						.requestMatchers("/", "/signup", "/login*", "/error*").permitAll()
						// 그외 나머지 차단
						.anyRequest().authenticated())

		// 인증 : 시스템에 대한 접근 유무
		;

		return http.build();
	}
}

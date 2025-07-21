package com.oracle.oBootSeqcurity01.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.HttpMessageConverterAuthenticationSuccessHandler.AuthenticationSuccess;

import com.oracle.oBootSeqcurity01.handler.FormAccessDenidHandler;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true) // Controller에서 @Secured,@PreAuthorize 사용을 위한 설정
@RequiredArgsConstructor
public class SecurityConfig {
	//Provider : 정보 제공자
	private final AuthenticationProvider authenticationProvider;
	private final AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> authenticationDetailsSource;
	// Handler : 이벤트에 대한 처리
	private final AuthenticationSuccessHandler successHandler;
	private final AuthenticationFailureHandler failureHandler;
	private final UserDetailsService userDetailsService;

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
						// 권한별 접근 가능한 경로
						.requestMatchers("/user").hasAuthority("ROLE_USER")
						.requestMatchers("/manager").hasAuthority("ROLE_MANAGER")
						.requestMatchers("/admin").hasRole("ADMIN")
						// ADMIN과 MANAGER는 admanager에 접근 가능
						.requestMatchers("/admanager").access(new WebExpressionAuthorizationManager("hasRole('ADMIN') or hasRole('MANAGER')"))
						// 그외 나머지는 인증만 되어있으면 접근 가능
						.anyRequest().authenticated()
						// 그외 나머지는 모두 차단
						//.anyRequest().denyAll()
						)
				
				// 인증 : 시스템에 대한 접근 유무
				.formLogin(form -> form.loginPage("/login")// UsernamePasswordAuthenticationFilter 생성폼 방식의 인증 처리
						// 자세한건 여기 참고
						.authenticationDetailsSource(authenticationDetailsSource)
						// 성공했을 때
						.successHandler(successHandler)
						// 실패했을 때
						.failureHandler(failureHandler).permitAll())
				
//				.userDetailsService(userDetailsService)
				.authenticationProvider(authenticationProvider)
				.exceptionHandling(exception -> exception.accessDeniedHandler(new FormAccessDenidHandler("/denied")));

		return http.build();
	}
}

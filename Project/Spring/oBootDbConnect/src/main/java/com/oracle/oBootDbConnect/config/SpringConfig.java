package com.oracle.oBootDbConnect.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.oracle.oBootDbConnect.repository.JdbcMemberRepository;
import com.oracle.oBootDbConnect.repository.MemberRepository;
import com.oracle.oBootDbConnect.repository.MemoryMemberRepository;

// Bean에 환경 설정 컴포넌트 지정
@Configuration
public class SpringConfig {
	private DataSource dataSource;
	public SpringConfig(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	//직접 Bean에 넣는다.
	@Bean
	public MemberRepository memberRepository() {
//		return new MemoryMemberRepository();// 서버의 메모리 저장 방식
		return new JdbcMemberRepository(dataSource);// DB 저장 방식
	}
}

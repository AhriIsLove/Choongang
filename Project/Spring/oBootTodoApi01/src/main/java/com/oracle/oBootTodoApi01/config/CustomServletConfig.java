package com.oracle.oBootTodoApi01.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.oracle.oBootTodoApi01.controller.formatter.LocalDateFormatter;

@Configuration
public class CustomServletConfig implements WebMvcConfigurer {
	@Override
	public void addFormatters(FormatterRegistry registry) {
		// TODO Auto-generated method stub
//		WebMvcConfigurer.super.addFormatters(registry);
		
		registry.addFormatter(new LocalDateFormatter());
	}
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		// TODO Auto-generated method stub
//		WebMvcConfigurer.super.addCorsMappings(registry);
		
		// '/*' : 현재 경로의 모든 파일
		// '/**' : 현재 경로의 모든 파일 + 폴더 + 하위폴더
		registry.addMapping("/**")
		.allowedOrigins("*")//허용할 출처(도메인)
		.allowedMethods("HEAD", "GET", "POST", "PUT", "DELETE", "OPTIONS")//허용 메시지 타입
		.maxAge(300)//허용 최대시간
		.allowedHeaders("Authorization", "Cache-Control", "Content-Type");//허용할 HTTP 헤더
		//*필수?
		//*Authorization : 사용자 인증 정보를 전달하는 헤더
		// - 클라이언트가 서버의 보호된 리소스에 접근할 수 있는 권한이 있음을 증명하는 데 사용
		//*Cache-Control : 브라우저 캐싱 동작을 제어하는 헤더
		// - 리소스가 얼마나 오래 캐시될지, 어떻게 캐시될지를 지정
		//Content-Type : 리소스의 미디어 타입(MIME 타입)을 나타내는 헤더
		// -  클라이언트에게 반환되는 콘텐츠의 형식을 알려줌
	}
}

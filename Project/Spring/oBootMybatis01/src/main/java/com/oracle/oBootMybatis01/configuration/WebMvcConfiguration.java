package com.oracle.oBootMybatis01.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.oracle.oBootMybatis01.service.SampleInterceptor;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
//		WebMvcConfigurer.super.addInterceptors(registry);
		
		// 누군가 URL /interCeptor --> SampleInterceptor() 처리 해줌
		registry.addInterceptor(new SampleInterceptor()).addPathPatterns("/interCeptor");
		
		//interCeptor*로 하면 interCeptor로 시작하는 모든 URL를 intercept한다.
//		registry.addInterceptor(new SampleInterceptor()).addPathPatterns("/interCeptor*");
	}
}

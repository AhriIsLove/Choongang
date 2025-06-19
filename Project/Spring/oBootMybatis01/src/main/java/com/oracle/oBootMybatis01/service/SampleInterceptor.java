package com.oracle.oBootMybatis01.service;

import java.lang.reflect.Method;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SampleInterceptor implements HandlerInterceptor {
	public SampleInterceptor() {
		// TODO Auto-generated constructor stub
	}
	
	// 3번 인터셉터
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("postHandle o((>ω< ))o S");
//		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
		
		String ID = (String) modelAndView.getModel().get("id");
		int memCnt = (int) modelAndView.getModel().get("memCnt");
		System.out.println("postHandle o((>ω< ))o memCnt : " + memCnt);
		if(memCnt < 1) {
			request.getSession().setAttribute("ID", ID);
			response.sendRedirect("doMemberWrite");
		}
		else {
			request.getSession().setAttribute("ID", ID);
			response.sendRedirect("doMemberList");
		}
	}
	
	// 1번 인터셉터
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("preHandle o((>ω< ))o S");
//		return HandlerInterceptor.super.preHandle(request, response, handler);
		
		HandlerMethod method = (HandlerMethod) handler;
		Method methodObj = method.getMethod();
		System.out.println("preHandle o((>ω< ))o Bean : " + method.getBean());
		System.out.println("preHandle o((>ω< ))o Method : " + methodObj);

		System.out.println("preHandle o((>ω< ))o E");
		return true;
	}
}

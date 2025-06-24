package com.oracle.oBootMybatis01.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

//AOP(Aspect Oriented Programming)설정 : 공통 기능 캡슐화
@Aspect
//Component화 하여 Controller/Service/Repository 처럼 실행시 등록
@Component
public class LogAop {
	//관심사가 주 프로그램의 어디에 횡단할 것인지 나타내는 위치
	@Pointcut("within(com.oracle.oBootMybatis01.dao.EmpDao*)")
	private void pointcutMethod() {
		
	}
	
	//target 객체의 메소드 실행 전에 실행(인터셉터와 비슷)
	// - pointcutMethod()의 Pointcut
	// -- within(com.oracle.oBootMybatis01.dao.EmpDao*)
	// --- com.oracle.oBootMybatis01.dao.EmpDao에서 발생하는 모든 기능들
	@Around("pointcutMethod()")
	public Object loggerAop(ProceedingJoinPoint joinPoint) throws Throwable {
		//호출한 함수정보
		String signatureStr = joinPoint.getSignature().toString();
		
		//시작시간 측정
		System.out.println(signatureStr + " IS START");
		long st = System.currentTimeMillis();
		
		try {
			// 핵심 관심사 기능 실행
			// - com.oracle.oBootMybatis01.dao.EmpDao.totalEmp, listEmp... 등
			Object obj = joinPoint.proceed();
			return obj;
		} finally {
			// 기능 실행 후 종료시간 측정
			long et = System.currentTimeMillis();
			System.out.println(signatureStr + " IS FINISHED");
			// 결과시간 측정
			System.out.println(signatureStr + "경과시간 : " + (et-st) + "ms");
		}
	}
	
	//이것도 있다...
	@Before("pointcutMethod()")
	public void beforeMethod() {
		System.out.println("AOP beforeMethod!!!");
	}
}

package springAOP;

import org.springframework.context.support.GenericXmlApplicationContext;

import aop.Calculator;
import aop.CalculatorImpl;

public class Ex01 {

	public static void main(String[] args) {
		// 공통 기능 Aspect
		// 공통 기능을 언제 적용하는지 Advice
		// 적용시키는 것을 Weaving
		// 적용할 수 있는 지점 Joinpoint
		// 실제로 Advice가 적용되는 Joinpoint(지점) Pointcut

		// aop.advice 패키지 안에 
		//    ㄴ LogPrintAroundAdvice.java  -  around advice 생성
		
		// ex) 로그인 확인은 핵심 기능 전이니까 Before Advice
		// 	현재 예제는 전과 후의 시간차이를 구해야하기 때문에 Around Advice
		/*
		Calculator calc = new CalculatorImpl();
		System.out.println(calc.add(4, 2));
		*/
		
		/*
		 * 핵심기능은 Calculator
		 * 보조기능은 LogPrintAroundAdvice
		 * xml 파일에서 가짜 프록시를 만들어서 Pointcut과 Advice 설정
		 */
		
		String resourceLocations = "applicationContext.xml";
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(resourceLocations);
		Calculator realCalc = ctx.getBean("calc", CalculatorImpl.class); // 로그 기록은 안남기고 결과만 출력됨
		Calculator fakeCalc = ctx.getBean("calcProxy", Calculator.class); // 가짜 프록시(보조기능이 장착된)
		
		System.out.println("realCalc : " + realCalc.add(4, 2));
		System.out.println("fakeCalc : " + fakeCalc.add(4, 2));
		
		// Before Advice 생성 추가 - aop.advice.LogPrintBeforeAdvice
		// After Advice 생성 추가 - aop.advice.LogPrintAfterReturningAdvice
		// Q. xml 파엘이 Advice 등록한 순서에 따라 호출되어지는가? yes
		// Before -> 함수 실행 -> After -> 결과
		// Around가 있으면 Around는 등록한 순서에 따라 먼저 호출되거나 나중에 호출되어짐
		System.out.println("=END=");

	} // main

} // class

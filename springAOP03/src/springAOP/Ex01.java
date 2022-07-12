package springAOP;

import org.springframework.context.support.GenericXmlApplicationContext;

import aop.Calculator;
import aop.CalculatorImpl;

public class Ex01 {

	public static void main(String[] args) {
		/*
	       * 스프링 AOP 3가지 방법 중에
	       * *** [ XML 스키마 기반 AOP 구현 ] *** p209
	       * (처리과정)
	       *  1) 스프링 AOP를 사용하기 위해 jar 의존파일 추가
	       *     com.springsource.org.aspectj.weaver-1.6.8.RELEASE.jar
	       *  2) aop.advice 패키지              --      삭제
	       *           ㄴ Before, After ,Around advice 3가지
	       *      			 --> 공통 기능을 제공할 클래스 추가   
	       *      		 aop.LogPrintProfiler.java 
	       *                 trace() 구현 - Around Advice
	       *  3) xml 설정 파일 
	       *    ㄴ <aop:config>  aop 설정하는 태그
	       *     Aspect를 설정 (공통 기능)
	       *     Advice를 어떤 Pointcut에 적용할지를 설정(지정)
	       * */

		String resourceLocations = "applicationContext.xml";
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(resourceLocations);
		Calculator calc = ctx.getBean("calc", Calculator.class); // 가짜 프록시가 없기 때문에 실제 클래스를 호출(실제 클래스에 장착이 되어짐)
		
		System.out.println("calc : " + calc.add(4, 2));

		System.out.println("=END=");

	} // main

} // class

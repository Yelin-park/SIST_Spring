package springAOP;

import org.springframework.context.support.GenericXmlApplicationContext;

import aop.Calculator;
import aop.CalculatorImpl;

public class Ex01 {

	public static void main(String[] args) {
		// springAOP04 프로젝트
		
		/* p226 @Aspect 어노테이션 사용하는 방법
		 * 	1. @Aspect 어노테이션을 이용해서 Aspect 클래스를 구현한다.
		 * 		- Aspect 클래스는 Advice를 구현한 메서드와 Pointcut 표현
		 * 	2. xml 파일 VS 자바코딩 설정에 따라 달라지는데 수업때는 xml 파일 설정 사용
		 * 		xml 파일은 
		 * 		<aop:aspectj-autoproxy/> 설정
		 * 
		 * 		자바 코딩 설정은 아래 애노테이션 추가
		 * 		@Configuration
		 * 		@EnableAspectJAutoProxy
		 * 
		 */

		String resourceLocations = "applicationContext.xml";
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(resourceLocations);
		Calculator calc = ctx.getBean("calc", Calculator.class); // 가짜 프록시가 없기 때문에 실제 클래스를 호출(실제 클래스에 장착이 되어짐)
		
		System.out.println("calc : " + calc.add(4, 2));

		System.out.println("=END=");

	} // main

} // class

package springAOP;

import org.springframework.context.support.GenericXmlApplicationContext;

import aop.Calculator;
import aop.CalculatorImpl;

public class Ex01 {

	public static void main(String[] args) {
	
		// [예외발생]
		// Exception in thread "main" org.springframework.beans.factory.BeanCreationException: - 스프링빈 객체 생성하다가 예외 발생했다.
		// Error creating bean with name 'calcProxy' defined in class path resource [applicationContext.xml]:
		// Cannot resolve reference to bean 'calc' while setting bean property 'target'; - calc 라는 스프링 빈을 못찾는다.
		// nested exception is org.springframework.beans.factory.NoSuchBeanDefinitionException: No bean named 'calc' is defined
		
		// CalculatorImpl에 이름을 설정해 주지 않으면 calculatorImpl 이름이 되어짐
		
		// applicationContext.xml 파일에서 컴포넌트 스캔을 사용해서 코딩 수정
		String resourceLocations = "applicationContext.xml";
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(resourceLocations);
		Calculator calc = ctx.getBean("calcProxy", Calculator.class); // 가짜 프록시(보조기능이 장착된)
		
		System.out.println("calc : " + calc.add(4, 2));

		System.out.println("=END=");

	} // main

} // class

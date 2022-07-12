package springAOP;

import aop.Calculator;
import aop.CalculatorImpl;

public class Ex01 {

	public static void main(String[] args) {
		/*
		 * p204 Chapter06 스프링AOP(챕터 3,4,5 일단 skip)
		 * [스프링의 핵심 기능 : DI, AOP]
		 * 문제를 바라보는 관점을 기준으로 프로그래밍하는 기법
		 * AOP는 문제를 해결하기 위한 핵심 관심 사항과 전체 적용되는 공통 관심 사항을 기준으로 프로그래밍 함으로써 공통 모듈을 여러 코드에 쉽게 적용할 수 있도록 도와준다.
		 * 
		 * 1. AOP
		 * 	- Aspect Oriented Programming의 약자로 관점 지향적인 프로그래밍 기법(방법)
		 *  - 2가지 관점(2가지 관점으로 나눠서 작업한다)
		 *  	1) 공통적인 부분	: 인증처리 부분 / 트랜잭션, 보안 부분
		 *  		== 공통 관심 사항(cross-cutting concern)
		 *  	2) 실제 본연의 업무(담당하는 로직) 부분 : 글쓰기, 글수정, 글삭제 부분
		 *  		== 핵심 관심 사항(core concern)
		 *  
		 *  ex) 로그인(인증) 되어야지만
		 *  글쓰기 write.do -> WriteHandler 세션안에 인증정보가 담겨져 있는지 물어보고 null이라면 login.do(로그인 요청으로 이동)
		 *  글수정 edit.do -> EditHandler 세션안에 인증정보가 담겨져 있는지 물어보고 null이라면 login.do(로그인 요청으로 이동)
		 *  글삭제 delete.do -> DeleteHandler 세션안에 인증정보가 담겨져 있는지 물어보고 null이라면 login.do(로그인 요청으로 이동)
		 *   가능
		 * 
		 * 트랜잭션 ex)
		 * 	계좌이체
		 * 	 트랜잭션 - AOP로 되어짐	
		 * 		A 통장 10,000원 출금
		 * 		B 통장 10,000원 입금
		 * 	  트랜잭션
		 * 
		 * 2. AOP
		 * 관점 지향적 프로그래밍 기법
		 * 공통 기능 + 핵심 기능
		 * 필터
		 * 
		 * 3. p205 AOP 용어 ***
		 * 	1) Aspect : 공통으로 적용되는 기능
		 * 	2) Advice : 공통 기능 [언제] 핵심 기능에 적용할지를 정의
		 * 		전/후/ 전+후 등등 5가지 종류
		 * 	3) Weaving : Advice를 핵심 로직 코드에 삽입(적용)하는 것
		 * 	4) Joinpoint : Advice를 적용할 수 있는 지점
		 * 	5) Pointcut : Advice를 실제 적용한 지점
		 * 
		 * 4. p206 세가지의 Weaving 방식
		 * 	1) 컴파일
		 * 	2) 클래스 로딩시
		 * 	*** 3) 런타임 시(실행할 때) *** - 프록시(proxy) 기반의 AOP 지원하기 때문에 Joinpoint 메서드만 사용할 수 있다.
		 * 
		 * 5. p207 스프링에서 AOP를 구현하는 3가지 방법
		 * 	1) 스프링 API 이용한 AOP 구현 (제일 귀찮고 번거로운 작업이라 사용하지 않을 예정)
		 * 	2) XML 스키마 기반의 POJO 클래스를 이용한 AOP 구현 - xml 파일
		 * 	3) AspectJ에서 정의한 @Aspect 애노테이션 기반의 AOP 구현
		 *
		 * 6. Advice 종류 - 언제 공통 기능이 핵심 기능에 적용되는지(시점)
		 * 	1) 전 Before Advice
		 * 	2) 후(오류와 상관X) After Advice
		 * 	3) 오류가 발생할 때 적용 After Throwing Advice
		 * 	4) 오류가 발생하지 않았을 경우 After Returning Advice
		 * 	5) 전 + 후 Around Advice
		 * 
		 * 7. [springAOP 프로젝트]	AOP 없이!
		 * 	aop.Calculator.java 인터페이스 - 계산기(덧셈, 뺄셈, 곱셈, 나눗셈 메서드)
		 * 	aop.CalculatorImpl.java 클래스
		 *  Ex01.java main() 메서드 안에서 CalculatorImpl 객체 생성해서 계산 작업
		 */
		
		Calculator calc = new CalculatorImpl(); // 업캐스팅
		
		// +, -, *, /   산술연산자 계산한 처리 시간도 출력
		// 메서드에서 시간을 계산하는 공통 기능을 따로 빼서 마치 하나의 공통 기능을 코딩한 것처럼 하는 것을 Weaving(Advice(공통기능)를 핵심 로직 코드에 삽입(적용)하는 것)
		System.out.println(calc.add(4, 2));
		System.out.println(calc.sub(4, 2));
		System.out.println(calc.mult(4, 2));
		System.out.println(calc.div(4, 2));
		
		System.out.println("=END=");
	} // main

} // class

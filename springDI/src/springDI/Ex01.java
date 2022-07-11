package springDI;

import di.RecordImpl;
import di.RecordViewImpl;

public class Ex01 {

	public static void main(String[] args) {
		/*
		 *  Class 폴더 안에 SpringClass 폴더 생성
		 *  이클립스 Encoding 등 환경설정 
		 *  javaProject -> springDI 생성 후 Ex01.java 생성
		 */
		
		System.out.println("익수니");
		
		/*
		 * 1. 스프링 프레임 워크 개요
		 * 	RestTemplate -> ajax
		 * 	- 스프링은 자바 개발자라면 무조건 알아야 되는 표준 프레임워크
		 * 	- 스프링 버전 3.0 / 4.0 / 5.0 (다 배움..)
		 * 		1) 스프링의 소개
		 * 		2) 스프링의 핵심 : 스프링 DI, 스프링 AOP ***
		 * 		3) 스프링 빈
		 * 		4) 스프링 MVC
		 * 		5) 스프링 JDBC
		 * 		6) ORM 연동 지원 : 하이버네이트, JPA, [Mybatis]
		 * 		7) 스프링 시큐리티(보안) - 인증, 권한 처리
		 * 		8) 메이븐(Maven) ?
		 * 
		 * 	p28 ~ p128 읽어보기
		 * 	6. Chapter 01 스프링 시작하기
		 * 	Chapter 02 스프링 DI를 이용한 객체 생성
		 * 	https://spring.io/
		 * 
		 * 2. [스프링의 주요 모듈]
		 * 	1) 스프링 프레임워크 - 스프링을 이용해서 어플리케이션을 개발할 때 기반되는 프레임워크 (DI, AOP 기능)
		 * 	2) 스프링 데이타(JDBC) - 데이터를 연동하는 API 제공
		 * 	3) 스프링 시큐리티(보안) - 인증/권한
		 * 								ㄴ> 이번 프로젝트 때는 세션(인증/권한), 필터 사용
		 * 	4) 스프링 배치(layout) - 페이지 모듈화
		 * 	5) 스프링 인터그레이션 - 시스템 간의 연동을 위한 메시징 프레임 워크
		 * 	6) 스프링 소셜 - 트위터, 페이스북 등 소셜 네트워크 연동
		 * 
		 * 3. 스프링 설치 + 주요 모듈
		 * 	1) 빌드 도구 : [메이븐], 그래들
		 * 		- 빌드? 컴파일 + 실행 < 더 큰 의미 프로젝트 생성 -> 컴파일 -> 배포 (모든 것을 관여)
		 * 	2) 메이븐은 jar 파일들을 중앙 저장소(central repository)라는 서버로부터 모듈 관리(다운받아서 설치)
		 * 		ex) jspPro에는 필요한 XXX.jar 파일을 WEB_INF/lib 안에 XXX.jar 파일을 넣었음
		 * 			메이븐은 그렇게 하지 않는다!
		 * 	3) pom.xml 파일에 코딩만 하면 중앙 저장소로 부터 로컬 저장소로 .jar(라이브러리) 파일을 다운받아서 설치한다.	
		 * 
		 * 4. p90 스프링 프레임워크의 주요 모듈
		 * 	표 1.2
		 * 		1) spring-beans : 스프링 컨테이너(공장)를 이용해서 객체를 생성하는 기본 기능을 제공하는 모듈
		 * 		2) spring-context : 객체 생성, 객체 생성된 것의 라이프 사이클 처리, 스키마 확장 등의 기능을 제공하는 모듈
		 * 		3) spring-aop : AOP(Aspect Oriented Programming) 기능 제공하는 모듈
		 * 						ㄴ> 관점 지향적인 프로그래밍 기법
		 * 		4) spring-web : REST 클라이언트, 데이터 변환 처리, 서블릿 필터, 파일 업로드 지원 등의 '웹 개발에 필요한 기능을 제공하는 모듈'
		 * 		5) spring-webmvc : 스프링 기반의 MVC 프레임워크 패턴으로 개발할 수 있도록 기능을 제공하는 모듈
		 * 		등등
		 * 
		 * 
		 * [ Chapter 02 스프링 DI ]
		 * 		객체를 생성 + 사용할 수 있도록... => 스프링에서 어떻게 해주냐? => 이런 설계 패턴을 DI(의존성 주입)라고 함
		 * 		예)
		 * 			ListHandler.java			ListService.java
		 * 			process(){					{
		 * 			    ListService				  select(){
		 * 			}															
		 * 												}	
		 * 										}
		 * 
		 * 5. p50 의존 객체를 직접 생성하는 방식의 단점
		 * 
		 * 6. p51 DI를 사용하는 방식의 코드 : 의존 객체를 외부에서 조립함(의존 객체를 직접 생성하는 방식의 장점?)	
		 * 
		 * 7. p55 생성자 방식과 프로퍼티 설정 방식
		 * 
		 * 8. p57 스프링은 객체를 생성하고 연결해주는 [DI 컨테이너 == 스프링 컨테이너 == IOC 컨테이너]
		 * 
		 * 9. p59 스프링 컨테이너의 5가지 종류
		 *
		 * 여기서 끊고! 위의 설명을 예제로 다루기!
		 */
		/*
		 *  di 패키지 추가
		 *  	ㄴ Record 인터페이스 - 추상메서드(총점, 평균 계산하는)
		 *  	ㄴ RecordImple 클래스 - 추상메서드 오버라이딩, 국영수 필드 추가
		 *  	ㄴ RecordView 인터페이스 - 추상메서드(성적 정보를 입력, 출력)
		 *  	ㄴ RecordViewImpe 클래스 - 추상메서드 오버라이딩
		 *  
		 *  예) 한 학생의 성적 정보(국,영,수)를 입력받고 출력하는 코딩
		 */
		
		RecordImpl record = new RecordImpl();
		
		// 생성자를 통해서 DI 방식
		// RecordViewImpl rvi = new RecordViewImpl(record);
		
		// 프로퍼티(setter)를 통해서 DI 방식
		RecordViewImpl rvi = new RecordViewImpl();
		rvi.setRecord(record);
		rvi.input(); // 학생 정보 입력
		
		rvi.output(); // 학생 정보 출력
		System.out.println("=END=");

	} // main

} // class

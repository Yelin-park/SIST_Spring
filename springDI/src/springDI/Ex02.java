package springDI;

import org.springframework.context.support.GenericXmlApplicationContext;

import di.RecordImpl;
import di.RecordViewImpl;

public class Ex02 {

	public static void main(String[] args) {
		// Ex01.java에서는 스프링 사용X
		// [ Ex02.java에서는 스프링 주요 모듈을 사용해서 RecordViewImpl 객체 생성해서 성적 입력, 출력하는 코딩을 하겠다. ]
		// RecordViewImpl 과 RecordImpl은 의존관계가 있다.
		// 			Spring DI ? 

		/*
		 * 1. 스프링 주요 모듈(jar) 추가
		 * 	1) 메이븐(빌드 도구)을 사용하면 pom.xml에 <dependency></dependency> 코딩하면 자동으로 다운+설치
		 * 	2) 지금은 메이븐 프로젝트가 아니기 때문에 라이브러리에 .jar 파일 추가
		 * 		ㄴ Referenced Libraries 폴더 생성 + 스프링의 주요 모듈 jar 파일 모두 추가
		 */
		
			/*
			스프링에서는 아래와 같이 코딩을 하지 않음!(3줄 코딩 대신 아래 스프링 관련 코딩으로 대신함)
			RecordImpl record = new RecordImpl();
			RecordViewImpl rvi = new RecordViewImpl();
			rvi.setRecord(record);
			rvi.input(); // 학생 정보 입력
			
			rvi.output(); // 학생 정보 출력
			 */
		
		// 스프링 컨테이너 == IOC 컨테이너에서 [스프링 객체 == 스프링 빈]을 생성하고 라이프를 관리 (종류는 5가지가 있음)
		// ???ApplicationContext공장 : 스프링 객체(빈) 생성 + 연결(조립) ***
		// 설명서 == xml 파일  ==> GenericXmlApplicationContext
		// 경로 : C:/spring-framework-3.0.2.RELEASE-with-docs/docs/spring-framework-reference/htmlsingle/spring-framework-reference.html
		//			ㄴ> 3.2.1. Configuration metadata 링크태그 클릭 후 xml 코딩 복사하여 applicationContext.xml에 붙여넣기
		
		String resourceLocations = "applicationContext.xml"; // 설정파일(src에 생성함)
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(resourceLocations); // 설정파일을 통해서 객체 생성을 하겠다.
		// ctx가 생성되었다는 것은 벌써 스프링 컨테이너 안에 스프링 빈 객체가 생성되고 조립되어진 상태(xml 파일에서 설정했기 때문)
		
		RecordViewImpl rvi = (RecordViewImpl) ctx.getBean("rvi"); // xml에서 객체 생성 + 조립된 것을 가져옴(Object로 돌려주기 때문에 다운캐스팅 필요)
		rvi.input();
		rvi.output();
		
		// 실행을 시키니까 에러 발생 ( exception in thread "main" java.lang.noclassdeffounderror: org/apache/commons/logging/logfactory )
		// 스프링 주요 모듈 추가 + 의존 모듈 추가 commons-logging.jar (C:\spring-framework-3.0.2.RELEASE-dependencies\org.apache.commons\com.springsource.org.apache.commons.logging\1.1.1)
 
		

		
		
	} // main

} // class

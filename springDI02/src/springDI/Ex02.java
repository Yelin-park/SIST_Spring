package springDI;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import di.Config;
import di.RecordViewImpl;

public class Ex02 {

	public static void main(String[] args) {
		// p66 ~ p85 XML을 이용한 DI 설정
		/*
		String resourceLocations = "applicationContext.xml"; 
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(resourceLocations); 
		RecordViewImpl rvi = (RecordViewImpl) ctx.getBean("rvi"); 
		rvi.input();
		rvi.output();
		*/
		
		// p85 ~ p95 자바 코드를 이용한 DI 설정
		/*
		 * 1. applicationContext.xml 파일 삭제하고 xml 파일 대신에 자바 파일 사용 : Config.java 추가(di 패키지 안에)
		 * 2. Config.java 설정 파일로 스프링 컨테이너 사용
		 * 		p59 AnnotationConfigApplicationContext - 자바 코드를 설정 파일로 사용한 스프링 컨테이너
		 * 
		 */
		
		// [예외] Exception in thread "main" java.lang.IllegalStateException: CGLIB is required to process @Configuration classes.
		// [해결] cglib-2.2.0.jar 파일 추가 (com.springsource.net.sf.cglib-2.2.0)
		
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class); // 매개변수를 클래스 타입으로 줌
		// 스프링컨테이너(공장) = 스프링 빈 객체 생성 + 조립(DI)
		
		// RecordViewImpl rvi = (RecordViewImpl) ctx.getBean("rvi");
		// RecordViewImpl rvi = (RecordViewImpl) ctx.getBean("getRecordViewImpl"); // Config.java에서 name 속성을 안주면 함수명으로 가져옴
		RecordViewImpl rvi = ctx.getBean("rvi", RecordViewImpl.class); // 가져와서 직접 다운캐스팅을 하겠다.
		rvi.input();
		rvi.output();
		System.out.println("=END=");
	} // main

} // class

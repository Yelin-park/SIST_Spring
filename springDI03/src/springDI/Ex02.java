package springDI;

import org.springframework.context.support.GenericXmlApplicationContext;

import di.RecordViewImpl;

public class Ex02 {

	public static void main(String[] args) {
		// p103(~p114) 애노테이션을 이용한 객체 간 의존 자동 연결
		/*
		 * 1. xml 파일 사용
		 * 2. Config.java 자바 코딩 사용
		 * [ 3. 자동으로 의존 주입 ]
		 * 	@Autowired - 빈객체가 여러개 있어도 잘 생성되어짐 == 타입을 기준으로 빈 객체를 선택
		 *  @Resource - 빈 객체가 여러 개 있으면 이름을 기준으로 빈 객체를 선택
		 *  @Inject
		 *  
		 *  @Autowired 또는 @Resource 어노테이션을 사용하기 위해서 xml 파일에 아래와 같은 코드 추가
		 * 1) xmlns:context="http://www.springframework.org/schema/context"
		 * 2) http://www.springframework.org/schema/context
           	  http://www.springframework.org/schema/context/spring-context.xsd
           3) <context:annotation-config></context:annotation-config>
           		ㄴ 위의 태그는 다수의 스프링 전처리기 빈을 등록해줌(의존 자동 연결할 때 필요한 객체들을 등록하는 코딩)
		 */
		
		String resourceLocations = "applicationContext.xml";
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(resourceLocations);
		RecordViewImpl rvi = (RecordViewImpl) ctx.getBean("rvi");
		rvi.input();
		rvi.output();
		System.out.println("=END=");
	} // main

} // class

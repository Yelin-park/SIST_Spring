package springDI;

import org.springframework.context.support.GenericXmlApplicationContext;

import di.RecordViewImpl;

public class Ex02 {

	public static void main(String[] args) {
		// p115 컴포넌트 스캔을 이용한 빈 자동 등록 - 수백 개의 빈 객체를 직접 생성하지 않고 자동으로 생성되어짐
		/*
		 * 1) 컴포넌트 스캔?
		 * xml 파일에 <context:component-scan base-package="패키지명"></context:component-scan> 코드 추가
		 * 객체를 생성할 클래스에 @Autowired @Component 애노테이션 추가
		 * 
		 * rvi라는 이름으로 사용하면 예외 발생
		 * 컴포넌트 스캔에 의해서 빈객체 생성 + 등록될 때 그 이름(id)는 자동으로 클래스명의 소문자 이름이 되어진다.
		 * public class RecordViewImpl implements RecordView{} == recordViewImpl
		 */
		
		String resourceLocations = "applicationContext.xml";
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(resourceLocations);
		// RecordViewImpl rvi = (RecordViewImpl) ctx.getBean("rvi"); // Component로 이름을 등록했을 경우 사용 가능
		RecordViewImpl rvi = (RecordViewImpl) ctx.getBean("recordViewImpl");
		rvi.input();
		rvi.output();
		System.out.println("=END=");
	} // main

} // class

/*
 스프링 프레임워크 사용하면
 객체 생성 + 조립하는 코딩 X -> Sample s = new Sample(); X
 [ springDI 프로젝트 ]
 ApplicationContext 스프링 컨테이너 ctx 객체가 생성될 때 <- xml 설정 파일
 ctx.getBean(빈이름);
 
 [ springDI02 프로젝트 ]
 ApplicationContext 스프링 컨테이너 ctx 객체가 생성될 때 <- Config.java 설정 파일(자바 코딩)
 ctx.getBean(빈이름);
 
 [ springDI03 프로젝트 ] 의존하는 객체를 주입할 때 자동으로 의존 주입 @Autowired, @Resource, @Inject 어노테이션 사용 (xml에 어노테이션으로 사용하기 위한 코드 추가 필요)
 필드 선언 위에 @Autowired
 생성자 위에 @Autowired
 setter 위에 @Autowired

 [ springDI04 프로젝트 ]
 스프링 컨테이너 안에 스프링 빈 객체 수백개 이상이 됨 -> bean 태그가 수백개가 되어짐 + 의존관계가 있다면 DI 작업도 해야함
 이 모든 것들을 자동으로 하겠다!
  ㄴ 컴포넌트 스캔(스프링 기능)
	스캔 대상이 되는 클래스에 @Component 어노테이션만 붙이면 자동으로 스캔이 되는 대상이 되어짐 == 스프링 빈 객체가 생성
	xml 파일에  <context:component-scan base-package="부모패키지 자동으로 하위 패키지도 스캔"></context:component-scan> 코드를 추가
	스캔 대상이 된 클래스 필드 선언 위에 @Autowired 어노테이션을 주면 자동으로 주입까지 시켜줌
 */

/*
 * p118 Tip
 * @Component 어노테이션
 *  ㄴ @Service : 서비스클래스
 *  ㄴ @Repository : DAO 클래스
 *  ㄴ @Controller : MV[C] 컨트롤러클래스
 *  
 */

/*
 * p119 스캔 대상 클래스 범위 지정하기
 * <context:component-scan base-package="di">
 *  <context:include 특정패키지는/> 포함
 *  <context:exclude di.service 특정패키지는/> 제외
 *  </context:component-scan>
 */

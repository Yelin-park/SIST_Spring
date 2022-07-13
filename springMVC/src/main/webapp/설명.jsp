<%@ page contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>2022. 7. 13. - 오후 12:35:27</title>
<link rel="shortcut icon" type="image/x-icon" href="../images/SiSt.ico">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
<h3>index.jsp</h3>
<pre>
1. JSP/Servlet MVC 패턴
	1) MV[C] 컨트롤러 선언 DispatcherServlet.java == 서블릿
	
	2) commandHandler.properties 생성해서
	   list.do=ListHandler
		> commandHandlerMap에 넣었음

	3) CommandHandler 인터페이스 선언
	   ListHandler 클래스 선언 [M]VC
		세션, request.setAttribute("", );
		return null 리다이렉트
		return "list.jsp" 포워딩
	
	4) ListService 클래스
	   BoardDAO 인터페이스
	   BoardDAOImpl 클래스

2. 스프링 MVC 패턴 작업(Part02 Chapter07 p258)
	p268 기본 흐름과 주요 컴포넌트
	[그림 7.4 스프링 MVC 웹 요청 처리 과정] p268
	[표 7.1 스프링 MVC의 주요 구성요소] p269
	(완벽하게 이해하고 암기)

	스프링에서는 컨트롤러를 그냥 컨트롤러라고 부르지 않음!(JSP MVC와 다름) front 컨트롤러 부름

	웹브라우저[list.do] (1)요청 -> MV[C] front 컨트롤러 		-> (2)요청URI/컨트롤러 검색
					:DispatcherServlet 구성요소	<-	:HandlerMapping 구성요소
	
					-> (3)처리요청			-> (4) 요청 처리 [M]VC 컨트롤러
					:HandlerAdapter 구성요소        <- 처리 결과 리턴
	
					<- (6) 처리 결과를 리턴 
					Model Handler(MVC의 컨트롤러)의 결과물 저장
					ModelAndView 변환  
	
					-> (7) 처리 결과물을 보여줄 View 검색 
					:ViewResolver 구성요소
					
					<- (8) 검색된 View를 front Controller에게 알려준다.
					
					-> (9) View에게 화면에 보여줄 응답을 생성하라고 요청  -> (10) list.jsp
					:View 구성요소


MVC의 M에 해당하는 모델핸들러는 스프링에서는 컨트롤러
중앙 제어하는 디스패처 서블릿은 front 컨트롤러

list.do 요청 -> ListHandler 검색 -> front 컨트롤러인 :DispatcherServlet에게 전달 -> front 컨트롤러가
					
properties 파일 안만들고 HandlerMapping 스프링 빈 객체가 요청 URL과 매칭되는 컨트롤러(XXXHandler == 핸들러 객체) 검색


3. 스프링 MVC 프로젝트 생성
	1) Dynamic Web Project 생성 - springMVC
</pre>
<pre>
	1. WEB-INF
		ㄴ lib 폴더에 필요한 jar 파일 추가
		ㄴ 스프링 주요 모듈 jar 추가
		ㄴ 스프링 주요 모듈 + 의존 모듈
			C:\spring-framework-3.0.2.RELEASE-with-docs\dist 에서 jar 파일만 다 추가하기
			com.springsource.org.aopalliance-1.0.0.jar
        	com.springsource.org.apache.commons.logging-1.1.1.jar
        	com.springsource.org.aspectj.weaver-1.6.8.RELEASE.jar
        	com.springsource.net.sf.cglib-2.2.0.jar

	2. 3장 Spring MVC using Classic.zip
	
	SCOTT 계정에 테이블 생성 
		-- 공지사항
		CREATE TABLE NOTICES
		(
			"SEQ" VARCHAR2(10 BYTE), --글번호
			"TITLE" VARCHAR2(200 BYTE), --제목
			"WRITER" VARCHAR2(50 BYTE), --작성자
			"CONTENT" VARCHAR2(4000 BYTE), --내용
			"REGDATE" TIMESTAMP (6), --작성일
			"HIT" NUMBER, --조회수
			"FILESRC" VARCHAR2(500 BYTE)-- 첨부파일
		);
		
		-- 회원테이블
		CREATE TABLE "MEMBER"
		(	
		    "ID" VARCHAR2(50 BYTE), -- 회원ID(원래 UID인데 수정함)
		    "PWD" VARCHAR2(50 BYTE), -- 비밀번호
		    "NAME" VARCHAR2(50 BYTE), -- 이름
		    "GENDER" VARCHAR2(10 BYTE), --성별
		    "BIRTH" VARCHAR2(10 BYTE), --생일
		    "IS_LUNAR" VARCHAR2(10 BYTE),  --양력,음력판단
		    "CPHONE" VARCHAR2(15 BYTE),  --휴대폰번호
		    "EMAIL" VARCHAR2(200 BYTE), --이메일
		    "HABIT" VARCHAR2(200 BYTE), --취미
		    "REGDATE" DATE --가입일
		);
		
	공지사항 - notice 테이블
	customer 폴더 - 공지사항 쓰기, 목록, 수정, 상세보기 jsp 파일과 css 파일
	
	회원 - member 테이블
	joinus 폴더 - 회원가입(join.jsp + join.css), 로그인(logon.jsp + logon.css)
	
	메인페이지
	index.jsp + index.css
</pre>
<pre>
	[ 스프링 MVC ]
		ㄱ. DispatcherServlet 선언 X
		ㄴ. commandHandler.properties 선언 X
			list.do=ListHandler
			요청URL => 컨트롤러 검색 ? HandlerMapping 검색(만들지 않아도됨 설정만)
	*** ㄷ. web.xml에 서블릿 등록
		
		스프링 컨테이너 = 스프링 빈객체 생성 + 조립 역할
			ㄴ 서버(톰캣)가 시작될 때 스프링 컨테이너를 만들어줌
			(자바에서는 GenericXmlApplicationContext를 new 연산자로 생성을 해주었는데)
			Web에서는 WebXmlApplicationContext 객체 생성(클래스 사용) + 서블릿명-servlet.xml 파일을 찾음
																		ㄴ dispatcher-servlet.xml (web.xml에 만든 서블릿 이름이 dispatcher 이기 때문)
				> 서블릿명-servlet 이렇게 이름을 주면 자동으로 찾도록 되어있음(이름을 다르게 주면 설정을 따로 하면됨)
	
		ㄹ. [M]VC 컨트롤러 / VO / DAO 패키지 추가
			newlecture.vo 패키지 추가
				ㄴ Member.java
				ㄴ Notice.java
				
			newlecture.dao 패키지 추가
				ㄴ MemberDao.java
				ㄴ NoticeDao.java
		
		ㅁ. ~~~Service 클래스 파일 X
		
		ㅂ. controllers.customer 패키지 -- 공지사항 컨트롤러(JSP의 ~~Handler)
		[스프링 컨트롤러 개념]
		[M]VC의 모델
		NoticeController
		
		
		[JSP 개념]
			CommandHandler 인터페이스
				String process(request, response);
			ListHandler implements CommandHandler{
				String process(request, response){
					List<BoardDTO> list = Service.select();
					request.setAttribute("list", list);
					return "list.jps"
				}
			}
			
	
	[ JSP/Servlet MVC ]
	1. 컨트롤러 생성 + web.xml 등록
		ㄱ. DispatcherServlet 선언
		ㄴ. commandHandler.properties 선언
		ㄷ. web.xml 등록
	2. CommandHandler 인터페이스 선언
		ListHandler 클래스 생성(MVC의 M인 모델객체)
	
</pre>
</body>
</html>
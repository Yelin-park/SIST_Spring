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
				Model	결과물 저장
				ModelAndView

				-> (7) 처리 결과물을 보여줄 View 검색 
				:ViewResolver 구성요소
				
				<- (8) 검색된 View를 front Controller에게 알려준다.
				
				-> (9) Viewp에게 화면에 보여줄 응답을 생성하라고 요청  -> (10) list.jsp
				:View 구성요소


MVC의 M에 해당하는 모델핸들러는 스프링에서는 컨트롤러
중앙 제어하는 디스패처 서블릿은 front 컨트롤러

list.do 요청 -> ListHandler 검색 -> front 컨트롤러인 :DispatcherServlet에게 전달 -> front 컨트롤러가
					
properties 파일 안만들고 HandlerMapping 스프링 빈 객체가 요청 URL과 매칭되는 컨트롤러(XXXHandler == 핸들러 객체) 검색


3. 스프링 MVC 프로젝트 생성
	1) Dynamic Web Project 생성 - springMVC
</pre>
<pre>
	1. WEB-INF/lib 폴더에 필요한 jar 파일 추가
</pre>
</body>
</html>
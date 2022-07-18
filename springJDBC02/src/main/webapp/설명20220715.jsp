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
<h3>설명20220715.jsp</h3>
<pre>
	springMVC04 프로젝트를 복사 붙여넣기해서 springJDBC 프로젝트 생성
	
	p475 Part03 데이터베이스 연동
	Chapter 11 데이터 베이스 연동 지원과 JDBC 지원 - 스프링 JDBC
	Chapter 12 스프링 트랜잭션 관리 - 스프링 Transaction
	Chapter 13 ORM 연동지원 - mybatis 연동할 떄 배울 것
	Chapter 14 스프링 데이터 JPA 소개
</pre>
<pre>
	Chapter 11 데이터 베이스 연동 지원과 JDBC 지원 - 스프링 JDBC
	
	스프링 JDBC / ORM 프레임워크(하이버네이트, JPA, [mybatis]) + 스프링
	1. DataSource 객체 p478 ~ p504 
		- 자바에서 DB와 연결하는 방법에는 여러가지가 있는데 그 중에서 스프링은 DataSource 방식을 사용
		- DataSource 설정하는 3가지 방법
			1) 커넥션 풀(DBCP) *** 얘도 사용
			2) JNDI(웹로직, JBOSS) - 톰캣
			3) 로컬 테스트 목적으로 Drivermanager를 이용(테스트용도) *** 사용해보고
		- mybatis 연동할 때도 사용
		
		테스트 용도로 예제)
		dispathcer-servlet.xml 스프링빈으로 등록(p482)
		  <bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
  			<property name="driverClassName" value="oracle.jdbc.driver.OracleDriver"></property>
   			<property name="url" value="jdbc:oracle:thin:@localhost:1521:xe"></property>
    		<property name="username" value="scott"></property>
    		<property name="password" value="tiger"></property>
 		 </bean>
 		 
 	2. 스프링 템플릿 클래스 제공
 	 - DB 연동할 때 항상 하는 중복 코딩 제거
 	 - 아래 코딩으로 중복 제거
 	 	JdbcTemplate
 	 	NamedParameterJdbcTemplate
 	 	SimpleJdbcInsert
 	 	SimpleJdbcCall	
		
	3. JdbcTemplate 메서드
		1. 조회 : query() - select * from board => 여러 개의 레코드를 조회할 때
		2. 조회 : queryForList() - select count(*) from board 쿼리 => 단일 컬럼 값을 조회할 때
		3. 조회 : queryForObject() - select * from board where seq=1 => 단일 레코드 조회
		4. 삽입, 수정, 삭제 : update()
		등등
		dispathcer-servlet.xml에서 JdbcTemplate 스프링 빈으로 등록 p486
		
	4. NoticeDao, MemberDao - DAO들만 건드리면 된다.
		처리 구문을 jdbcTemplate 스프링 빈을 사용하는 코딩으로 수정
		1) 공지사항 목록 조회 메서드 수정 - getNotices()
		
		
	p475
	String sql
	매번 아래와 같은 작업을 반복적으로 함
	
	con/stmt/rs
	rs = execute
	con/stmt.close()
	
	반복적으로 하지 않도록 틀을 만들어서 찍어냄
	붕어빵 틀 = 템플릿 클래스
	
	
</pre>
</body>
</html>
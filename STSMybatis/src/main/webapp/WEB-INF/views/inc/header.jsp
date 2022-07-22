<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags" %><!-- JSP 태그 라이브러리를 사용하기 위해 추가 -->
<!-- webapp/inc/header.jsp -->
<div id="header">
	<div class="top-wrapper">
		<h1 id="logo">
			<a href="${ pageContext.request.contextPath }/index.htm"><img src="${ pageContext.request.contextPath }/images/logo.png" alt="뉴렉처" /></a>
		</h1>
		<h2 class="hidden">메인메뉴</h2>
		<ul id="mainmenu" class="block_hlist">
			<li><a href="">학습가이드</a></li>
			<li><a href="">과정선택</a></li>
			<li><a href="">인기과정</a></li>
		</ul>
		<form id="searchform" action="" method="get">
			<fieldset>
				<legend class="hidden"> 과정검색폼 </legend>
				<label for="query">과정검색</label> <input type="text" name="query" />
				<input type="submit" class="button" value="검색" />
			</fieldset>
		</form>
		<h3 class="hidden">로그인메뉴</h3>
		<ul id="loginmenu" class="block_hlist">
			<li><a href="${ pageContext.request.contextPath }/index.htm">HOME</a></li>
			<li>
<%-- 			
				<!-- 로그인 X -->
				<!-- userPrincipal : 인증받은 사용자의 객체를 돌려줌 만약 로그인이 안되어있다면 null을 돌려줌 -->
				<c:if test="${ empty pageContext.request.userPrincipal }">
					<a href="${ pageContext.request.contextPath }/joinus/login.htm">로그인</a>
				</c:if>
				
				<!-- 로그인 O -->
				<c:if test="${ not empty pageContext.request.userPrincipal }">
					<!-- p711 표16.7 -->
					<a href="${ pageContext.request.contextPath }/j_spring_security_logout">
						[ ${ pageContext.request.userPrincipal.name} ]로그아웃
					</a>
				</c:if>	
--%>		
				<!-- 위에 있는 코딩을 스프링 시큐리티 JSP 태그 라이브러리 사용해서 수정 -->
				<!-- ifNotGranted="ROLE_USER" : 해당 권한을 가지고 있지 않다면 로그인을 하지 않은 상태 -->
				<s:authorize ifNotGranted="ROLE_USER, ROLE_ADMIN" >
					<a href="${ pageContext.request.contextPath }/joinus/login.htm">로그인</a>
				</s:authorize>
				
				<!-- ifAnyGranted="ROLE_USER, ROLE_ADMIN" : 둘 중의 하나의 권한이라도 있다면 로그인한 상태  -->
				<s:authorize ifAnyGranted="ROLE_USER, ROLE_ADMIN" >
					<a href="${ pageContext.request.contextPath }/j_spring_security_logout">
						[ ${ pageContext.request.userPrincipal.name} ]로그아웃
					</a>
				</s:authorize>
			</li>
			<li><a href="${ pageContext.request.contextPath }/joinus/join.htm">회원가입</a></li>
		</ul>
		<h3 class="hidden">회원메뉴</h3>
		<ul id="membermenu" class="clear">
			<li><a href=""><img src="${ pageContext.request.contextPath }/images/menuMyPage.png"
					alt="마이페이지" /></a></li>
			<li><a href="${ pageContext.request.contextPath }/customer/notice.htm"><img
					src="${ pageContext.request.contextPath }/images/menuCustomer.png" alt="고객센터" /></a></li>
		</ul>
	</div>
</div>

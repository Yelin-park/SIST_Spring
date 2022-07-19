<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %><!-- 스프링 타이즈를 위해 태그라이브러리 추가 -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<title>index</title>
		<link href="notice.css" type="text/css" rel="stylesheet" />
	</head>
	<body>
		<!-- 1. header 부분 시작 -->
		<tiles:insertAttribute name="header" />
		<!-- 1. header 부분 끝 -->
		<!-- 2. visual 부분 시작 -->
		<tiles:insertAttribute name="visual" />
		<!-- 2. visual 부분 끝 -->
		<div id="main">
			<div class="top-wrapper clear">
				<!-- 3. content 부분 시작 -->
				<tiles:insertAttribute name="content" />
				<!-- 3. content 부분 끝 -->
				<!-- 4. navi 부분 시작 -->
				<tiles:insertAttribute name="aside" />
				<!-- 4. navi 부분 끝 -->
			</div>
		</div>
		<!-- 5. footer 부분 시작 -->
		<tiles:insertAttribute name="footer" />
		<!-- 5. footer 부분 끝 -->
	</body>
</html>

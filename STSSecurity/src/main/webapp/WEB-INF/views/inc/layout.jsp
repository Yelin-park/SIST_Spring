<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<title><tiles:getAsString name="title" /></title>
		<link href="${pageContext.request.contextPath }/index.css" type="text/css" rel="stylesheet" />
		<%-- <link href="<tiles:getAsString name="css" />" type="text/css" rel="stylesheet" /> --%>
	</head>
	<body>
		<!-- 1. header 시작 -->
		<tiles:insertAttribute name="header" />
		<!-- 1. header 끝 -->
		<div id="main">
			<!-- 3. content 시작 -->
			<tiles:insertAttribute name="content" />
			<!-- 3. content 끝 -->			
		</div>
		<!-- 2. footer 시작 -->
		<tiles:insertAttribute name="footer" />
		<!-- 2. footer 끝 -->
	</body>
</html>

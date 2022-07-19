<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>index</title>
<link href="join.css" type="text/css" rel="stylesheet" />
</head>
<body>
	<!-- 1. header 시작 -->
	<tiles:insertAttribute name="header" />
	<!-- 1. header 끝 -->
	<!-- 3. visual 시작 -->
	<tiles:insertAttribute name="visual" />
	<!-- 3. visual 끝 -->
	<div id="main">
		<div class="top-wrapper clear">
			<!-- 5. content 시작 -->
			<tiles:insertAttribute name="content" />
			<!-- 5. content 끝 -->
			<!-- 4. navi 시작  -->
			<tiles:insertAttribute name="aside" />
			<!-- 4. navi 끝  -->
		</div>
	</div>
	<!-- 2. footer 시작 -->
	<tiles:insertAttribute name="footer" />
	<!-- 2. footer 끝 -->
</body>
</html>

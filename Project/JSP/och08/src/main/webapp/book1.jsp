<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 클래스 선언(pt) -->
	<jsp:useBean id="pt" class="och08.Book" scope="request"></jsp:useBean><!-- scope : 선언 범위(기본:page) -->
	
	<!-- pt에 모든데이터(*) Set -->
	<jsp:setProperty property="*" name="pt"/>
	
	코드0 : <jsp:getProperty property="code" name="pt"/><p>
	이름0 : <jsp:getProperty property="name" name="pt"/><p>
	가격0 : <jsp:getProperty property="price" name="pt"/><p>
	작가0 : <jsp:getProperty property="writer" name="pt"/><p>
	
	<!-- 페이지 이동 -->
	<jsp:forward page="productInfo.jsp"></jsp:forward>
</body>
</html>
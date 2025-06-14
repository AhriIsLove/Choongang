<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>제품정보</h1>
<!-- 클래스 선언(pt) -->
<%-- <jsp:useBean id="pt" class="och08.Product" scope="request"></jsp:useBean> --%>
<jsp:useBean id="pt" class="och08.Book" scope="request"></jsp:useBean>

코드1 : <jsp:getProperty property="code" name="pt"/><p>
이름1 : <jsp:getProperty property="name" name="pt"/><p>
가격1 : <jsp:getProperty property="price" name="pt"/><p>
작가1 : <jsp:getProperty property="writer" name="pt"/><p>

</body>
</html>
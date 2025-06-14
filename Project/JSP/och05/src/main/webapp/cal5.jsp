<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>연산결과 cal5.jsp</h1>
	<!-- web.xml파일을 수정하여 예외처리 -->
<%
	// 1. num1, num2 parameter 받기
	int num1 = Integer.parseInt(request.getParameter("num1"));
	int num2 = Integer.parseInt(request.getParameter("num2"));
	// 2. + , -, * , / 출력하기
	out.print(num1 + " + " + num2 + " = " + (num1 + num2) + "<p>");
	out.print(num1 + " - " + num2 + " = " + (num1 - num2) + "<p>");
	out.print(num1 + " * " + num2 + " = " + (num1 * num2) + "<p>");
	out.print(num1 + " / " + num2 + " = " + (num1 / num2) + "<p>");
%>
</body>
</html>
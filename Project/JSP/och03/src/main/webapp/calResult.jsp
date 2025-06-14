<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>calResult 연산결과</h1>
	덧셈 : <%=request.getAttribute("add") %> Object 객체<p>
	뺄셈 : <%=request.getAttribute("minus") %><p>
	곱셈 : <%=request.getAttribute("multi") %><p>
	나눗셈 : <%=request.getAttribute("division") %><p>
	원본값1 : <%=request.getParameter("num1") %> String 객체<p>
	원본값2 : <%=request.getParameter("num2") %><p>
</body>
</html>
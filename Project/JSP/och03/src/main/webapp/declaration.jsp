<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%!
//declaration 선언부 : 멤버변수, method를 사용
String name = "홍길동!";
%>

<%
String hi = name + "안녕!!!";
%>

<%=hi %>
</body>
</html>
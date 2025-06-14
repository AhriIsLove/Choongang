<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
String name = request.getParameter("name");
%>
<body>
	와우! 방가 방가<%=name%>님
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true" %>
    <%response.setStatus(200); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
Exception e = (Exception)request.getAttribute("exceptionError");

%>
	<h1>공지사항0</h1>
	공사중입니다...
	메시지 : <%=e.getMessage() %>
	<p>
</body>
</html>
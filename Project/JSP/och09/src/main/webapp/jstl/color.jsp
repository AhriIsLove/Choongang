<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	String[] color = { "red", "orange", "yellow", "green", "blue", "navy", "violet" };
	request.setAttribute("color", color);
	%>
	<%-- <jsp:forward page="colorResult.jsp"></jsp:forward> --%>
	<%-- <jsp:forward page="colorResult2.jsp"></jsp:forward> --%>
	<jsp:forward page="colorToken.jsp"></jsp:forward>
</body>
</html>
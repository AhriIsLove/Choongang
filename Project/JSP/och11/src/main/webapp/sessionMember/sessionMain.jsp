<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
String id = (String)session.getAttribute("id");
if(id == null || id == ""){
	response.sendRedirect("loginForm.jsp");
}
%>
<%=id %>님 환영합니다.<p>
<input type="button" value="로그아웃" onclick="location.href='sessionLogout.jsp'">
</body>
</html>
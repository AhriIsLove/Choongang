<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true" %><!-- 에러페이지 선언... JSP내에서 exception 객체 사용 가능 -->
<% response.setStatus(200); %><!-- 에러 상태 200(정상 코드) 강제 부여... -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>공지사항1</h1>
	공사중...
	<p>
</body>
</html>
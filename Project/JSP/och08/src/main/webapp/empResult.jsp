<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:useBean id="emp" class="och08.Emp" scope="request"></jsp:useBean>

사번 : <jsp:getProperty property="empno" name="emp"/> <p>
이름 : <jsp:getProperty property="ename" name="emp"/> <p>
업무 : <jsp:getProperty property="job" name="emp"/> <p>
급여 : <jsp:getProperty property="sal" name="emp"/> <p>
입사일 : <jsp:getProperty property="hiredate" name="emp"/> <p>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!--   
	// 1.  표준Tag                  person Bean 생성
	         setProperty : Expression
	                 1)    name    :  name
	                 2)    gender  :  gender
	                 3)     age    :  age
	// 2. personResult.jsp        jsp:getProperty  표기법
	 -->
	<%
	String name = request.getParameter("name");
	String gender = request.getParameter("gender");
	int age = Integer.parseInt(request.getParameter("age"));
	%>
	<jsp:useBean id="person" class="och08.Person" scope="request"></jsp:useBean>
	
	<jsp:setProperty property="name" name="person" value="<%=name %>"/>
	<jsp:setProperty property="gender" name="person" value="<%=gender %>"/>
	<jsp:setProperty property="age" name="person" value="<%=age %>"/>
	
	<jsp:forward page="personResult.jsp"></jsp:forward>
</body>
</html>
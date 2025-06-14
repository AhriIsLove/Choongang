<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- Person person = new Person(); -->
	<jsp:useBean id="person" class="och08.Person"></jsp:useBean>
	<%-- person.setName("<%=name %>"); --%>
	<jsp:setProperty property="name" name="person"/>
	<jsp:setProperty property="gender" name="person"/>
	<jsp:setProperty property="age" name="person"/>
	
	<h1>인적 사항</h1>
	<!-- person.getName(); -->
	이름 : <jsp:getProperty property="name" name="person"/><p>
	성별 : <jsp:getProperty property="gender" name="person"/><p>
	나이 : <jsp:getProperty property="age" name="person"/><p>
</body>
</html>
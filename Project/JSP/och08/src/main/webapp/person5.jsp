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
	         setProperty : 전체설정 
	// 2. personResult.jsp        jsp:getProperty  표기법         
	 -->
	<jsp:useBean id="person" class="och08.Person" scope="request"></jsp:useBean>
	
	<jsp:setProperty property="*" name="person"/>
	
	<jsp:forward page="personResult.jsp"></jsp:forward>
</body>
</html>
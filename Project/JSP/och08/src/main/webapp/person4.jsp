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
	                 1)    name    : 홍길동 , 
	                 2)    gender  : 남자
	                 3)     age    : 23
	// 2. personResult.jsp        jsp:getProperty  표기법      
	//     1)  <h2>인적 사항</h2>	
	       2) 이름 : 
	       3) 성별 : 
	       4) 나이 :
	 -->
	<jsp:useBean id="person" class="och08.Person" scope="request"></jsp:useBean>
	
	<jsp:setProperty property="name" name="person" param="name"/>
	<jsp:setProperty property="gender" name="person" param="gender"/>
	<jsp:setProperty property="age" name="person" param="age"/>
	
	<jsp:forward page="personResult.jsp"></jsp:forward>
</body>
</html>
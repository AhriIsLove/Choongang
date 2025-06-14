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
	// HW02
	// Bean  : emp, class : Emp
	// 모든 property 저장
	// 이동: empResult.jsp
	//        사번 : 
	//        이름 : 
	//        업무 : 
	//        급여 : 
	//       입사일 :    
-->
<jsp:useBean id="emp" class="och08.Emp" scope="request"></jsp:useBean>
<jsp:setProperty property="*" name="emp"/>
<jsp:forward page="empResult.jsp"></jsp:forward>
</body>
</html>
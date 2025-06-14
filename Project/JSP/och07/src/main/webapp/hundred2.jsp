<%@page import="java.io.Console"%>
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
	int sum = 0;
	for (int i = 1; i <= 100; i++) {
		sum += i;
	}
	request.setAttribute("sum1", sum);//request 변수를 주고 받는데에서만
	pageContext.setAttribute("sum2", sum);//현재 페이지에서만 
	
	//RequestDispatcher rd = request.getRequestDispatcher("hunResult2.jsp");
	//rd.forward(request, response);
	%>
	sum : ${sum }<p>
	sum1 : ${sum1 }<p>
	sum2 : ${sum2 }
</body>
</html>